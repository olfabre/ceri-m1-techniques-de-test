# TP5 Bulbapedia



## Épisode 1

Nous allons intégrer l'outil checkstyle dans notre processus afin de nous assurer que notre code respecte un ensemble de critères de qualité. Nous allons intégrer Checkstyle et l'utiliser avec CircleCI.

On pensera également à intégrer le badge dans le fichier README.md présentant une versions condensée des conclusions du rapport généré.



Etape 1:

Nous allons ajouter le plugin Checkstyle au fichier `pom.xml`

Modifiez la section `<plugins>` dans le fichier `pom.xml` pour inclure le plugin

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
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding> <!-- Encodage par défaut -->
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

            <!-- Plugin Checkstyle -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-checkstyle-plugin</artifactId>
                <version>3.1.2</version>
                <configuration>
                    <configLocation>checkstyle.xml</configLocation>
                    <encoding>UTF-8</encoding>
                    <consoleOutput>true</consoleOutput>
                    <failsOnError>true</failsOnError>
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



**`configLocation`** : Spécifie le fichier de configuration Checkstyle. Placez un fichier nommé `checkstyle.xml` dans le dossier `src/main/resources` ou à la racine du projet.

**`failsOnError`** : Provoque une erreur si Checkstyle trouve des violations



Etape 2:

Nous allons créer un fichier  `checkstyle.xml` pour définir les règles de style de code.

```xml
<!DOCTYPE module PUBLIC
        "-//Puppy Crawl//DTD Check Configuration 1.3//EN"
        "https://checkstyle.org/dtds/configuration_1_3.dtd">

<module name="Checker">
    <module name="TreeWalker">
        <module name="JavadocMethod">
            <property name="scope" value="public"/>
        </module>
        <module name="WhitespaceAround"/>
        <module name="AvoidStarImport"/>
        <module name="FinalLocalVariable"/>
    </module>
</module>

```

on place ce fichier au même niveau que pom.xml



Etape 3:

Nous allons ajouter Checkstyle à CircleCI

Pour cela, nous allons modifier notre fichier `.circleci/config.yml` pour exécuter Checkstyle pendant la pipeline.

```xml
# Use the latest 2.1 version of CircleCI pipeline process engine.
# See: https://circleci.com/docs/configuration-reference
version: 2.1

# Import the Codecov orb
orbs:
  codecov: codecov/codecov@4.0.1

# Define a job to be invoked later in a workflow.
jobs:
  build-and-test:
    docker:
      - image: cimg/openjdk:21.0
    steps:
      # Checkout the code as the first step.
      - checkout

      # Build the project
      - run:
          name: Build
          command: mvn -B -DskipTests clean package

      # Run tests
      - run:
          name: Test
          command: mvn test

      # Generate the JaCoCo report
      - run:
          name: Generate Code Coverage Report
          command: mvn jacoco:report

      # Add Checkstyle verification
      - run:
          name: Run Checkstyle Verification
          command: mvn checkstyle:check

      # Generate Checkstyle HTML report
      - run:
          name: Generate Checkstyle HTML Report
          command: mvn checkstyle:checkstyle

      # Upload the coverage report to Codecov
      - run:
          name: Upload to Codecov
          command: bash <(curl -s https://codecov.io/bash) -t $CODECOV_TOKEN -s target/site/jacoco -r "olfabre/ceri-m1-techniques-de-test"

workflows:
  sample:
    jobs:
      - build-and-test:
          filters:
            branches:
              only:
                - master

```



