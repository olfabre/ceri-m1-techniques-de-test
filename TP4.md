## TP4



### Mes explication sur le cours des tests

sites:

https://app.circleci.com/

https://app.codecov.io/github/olfabre

#### Épisode 1 - Code coverage

On souhaite obtenir des indicateurs de pertinence après avoir réalisé des tests.

Pour la pourcentage de couverture (Code Coverage), nous allons utiliser la plateforme https://about.codecov.io/

**Etape 1:** on doit s'identifier sur la plateforme avec ses identifications de GitHub 
voci mon lien: https://app.codecov.io/github/olfabre



**Etape 2:** sélection et ajout de notre repository

![4](explications_images/4.jpg)

**Etape 3:** dans les paramètrages, on choisi "Using Circle CI"

Copiez le token dans le press papier:
CODECOV_TOKEN = 68f12931-afbd-4264-9191-d4e36b630c80



Etape 4: configuration de circleCI dans notre projet


![5](explications_images/5.jpg)



nous avions le code 

```yaml
version: 2.1

executors:
  maven-executor:
    docker:
      - image: maven:3.8.4-openjdk-11  # Utilisation d'une image avec Maven et OpenJDK 11

jobs:
  build:
    executor: maven-executor  # Utilisation de l'exécuteur défini ci-dessus
    steps:
      - checkout  # Vérifier le code source
      - run:
          name: Run Maven tests
          command: mvn test  # Exécuter les tests unitaires avec Maven

workflows:
  version: 2
  build:
    jobs:
      - build:
          filters:
            branches:
              only:
                - master  # Exécu

```



Pour intégrer l'orb Codecov dans votre configuration existante tout en conservant votre exécuteur `maven-executor`, il vous suffit de faire quelques ajustements dans votre fichier `.circleci/config.yml`. Voici comment faire :

1. **Ajoutez l'orb Codecov** au début du fichier.
2. **Modifiez le workflow** pour inclure l'étape `codecov/upload` après l'exécution des tests.

Voici à quoi devrait ressembler votre fichier `.circleci/config.yml` après modification :

```yaml
# Use the latest 2.1 version of CircleCI pipeline process engine.
# See: https://circleci.com/docs/configuration-reference
version: 2.1

# Import the Codecov orb
orbs:
  codecov: codecov/codecov@4.0.1
# Define a job to be invoked later in a workflow.
# See: https://circleci.com/docs/jobs-steps/#jobs-overview & https://circleci.com/docs/configuration-reference/#jobs
jobs:
  # Below is the definition of your job to build and test your app, you can rename and customize it as you want.
  build-and-test:
    # Specify the execution environment. You can specify an image from Docker Hub or use one of our convenience images from CircleCI's Developer Hub.
    # See: https://circleci.com/docs/executor-intro/ & https://circleci.com/docs/configuration-reference/#executor-job
    docker:
      # Specify the version you desire here
      # See: https://circleci.com/developer/images/image/cimg/openjdk
      - image: cimg/openjdk:21.0

    # Add steps to the job
    # See: https://circleci.com/docs/jobs-steps/#steps-overview & https://circleci.com/docs/configuration-reference/#steps
    steps:
      # Checkout the code as the first step.
      - checkout

      # Use mvn clean and package as the standard maven build phase
      - run:
          name: Build
          command: mvn -B -DskipTests clean package

      # Then run your tests!
      - run:
          name: Test
          command: mvn test

      # Generate the JaCoCo report after running the tests.
      - run:
          name: Generate Code Coverage Report
          command: mvn jacoco:report # Lister le contenu du dossier

      # Add the Checkstyle report generation step
      - run:
          name: Generate Checkstyle Report
          command: mvn checkstyle:checkstyle  # Lister les rapports de tests

      # Upload the coverage report to Codecov.
      - run:
          name: Upload to Codecov
          command: bash <(curl -s https://codecov.io/bash) -t $CODECOV_TOKEN -s target/site/jacoco -r "olfabre/ceri-m1-techniques-de-test"



# Orchestrate jobs using workflows
# See: https://circleci.com/docs/workflows/ & https://circleci.com/docs/configuration-reference/#workflows
workflows:
  sample: # This is the name of the workflow, feel free to change it to better match your workflow.
    # Inside the workflow, you define the jobs you .. want to run.
    jobs:
      - build-and-test:
          filters:
            branches:
              only:
                - master
```



### Explications des ajouts :

- **`orbs`** : Cela déclare l'orb Codecov, qui simplifie l'étape d'envoi du rapport.
- **`codecov/upload`** : Cette étape upload le rapport de couverture généré par JaCoCo vers Codecov.
- **`token: CODECOV_TOKEN`** : Assurez-vous d'avoir ajouté le `CODECOV_TOKEN` dans les variables d'environnement de votre projet CircleCI sous **Project Settings > Environment Variables**.

Une fois ces modifications faites, validez et poussez les changements dans votre repository. CircleCI devrait alors exécuter les tests, générer les rapports de couverture et les publier automatiquement sur Codecov.



J'ai modifié le fichier pom.xml pour Maven



```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>fr.univavignon</groupId>
    <artifactId>ceri-m1-techniques-de-test</artifactId>
    <version>1.0-SNAPSHOT</version>
    <name>CERI M1 Techniques de Test</name>
    <description>Projet pour l'apprentissage des techniques de test d'API.</description>

    <properties>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
    </properties>

    <dependencies>
        <!-- Dépendance pour JUnit -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>test</scope>
        </dependency>

        <!-- Dépendance pour Mockito -->
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>3.12.4</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>

            <!-- Plugin JaCoCo pour la couverture de code -->
            <plugin>
                <groupId>org.jacoco</groupId>
                <artifactId>jacoco-maven-plugin</artifactId>
                <version>0.8.7</version> <!-- Utilisez la dernière version disponible -->
                <executions>
                    <execution>
                        <goals>
                            <goal>prepare-agent</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>report</id>
                        <phase>test</phase>
                        <goals>
                            <goal>report</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.22.2</version>
            </plugin>
        </plugins>
        <sourceDirectory>src/main/java</sourceDirectory>
        <testSourceDirectory>src/test/java</testSourceDirectory>
    </build>
</project>

```



#### Épisode 2 - Badger Badger Badger

Nous allons placer les badges CircleCI et pour la couverture de test sur mon fichier README.md

##### CircleCI

Dans Project Settings / Status Badges , un outil est disponible pour générer des badges de statut CircleCI.

[![CircleCI](https://dl.circleci.com/status-badge/img/circleci/7MzTkwBFxHK2MkeUAifbjS/2LAHbRt645vW3tJZXN6rXF/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/circleci/7MzTkwBFxHK2MkeUAifbjS/2LAHbRt645vW3tJZXN6rXF/tree/master)



##### CodeCov

Dans Configuration / Badges & Graphsn, un outil est disponible pour générér des badges de couverture pour mon dépôt **olfabre/ceri-m1-techniques-de-test**

[![codecov](https://codecov.io/github/olfabre/ceri-m1-techniques-de-test/branch/master/graph/badge.svg?token=4LFWQGVEUR)](https://codecov.io/github/olfabre/ceri-m1-techniques-de-test)





#### Épisode 3 - Make it work !

Je vais développer les implémentation des interfaces en maintenant un niveau de qualité optimal.

