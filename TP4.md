## TP4



### Mes explication sur le cours des tests



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
version: 2.1

orbs:
  codecov: codecov/codecov@4.0.1  # Ajout de l'orb Codecov

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
      - run:
          name: Generate Coverage Report
          command: mvn jacoco:report  # Générer le rapport de couverture de JaCoCo
      - codecov/upload:
          token: CODECOV_TOKEN  # Utiliser le token Codecov défini dans les variables d'environnement de CircleCI


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
                <version>0.8.9</version> <!-- Utilisez la dernière version disponible -->
                <executions>
                    <execution>
                        <goals>
                            <goal>prepare-agent</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>report</id>
                        <phase>verify</phase>
                        <goals>
                            <goal>report</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
        <sourceDirectory>src/main/java</sourceDirectory>
        <testSourceDirectory>src/test/java</testSourceDirectory>
    </build>
</project>


```



Ensuite j'ai activé dans Organization Settings/Advanced  de circleCI, Enable intelligent summaries of build failure messages pour avoir la raison de l'erreur avec IA de circileCI



