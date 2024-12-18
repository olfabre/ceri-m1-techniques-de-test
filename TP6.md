# TP6 : Ils sont de retour pour vous jouer un mauvais tour



## Épisode 1 - Giovanni's touch



Une implémentation de `IPokemonFactory`  a été remise et qui est meilleur que la notre.

On doit intégrer cette implémentation dans notre projet et la passez au crible grâce à notre suite de tests et au travers d'une revue de code, on doit rédigez un succint rapport qui présente nos conclusions. Certains défauts de leur implémentation ne sont peut-être pas couverts par les tests que nous avons déjà mis en place, on ne doit pas hésitez pas à relever ces défauts aussi, et, si nous y parvenons, à mettre en place des tests pour les détecter automatiquement.

**Etape 1: nous intégrons la nouvelle dépendance pour faire fonctionner le code améliorée**

La dépendance **`commons-collections4`** d'Apache est une bibliothèque Java qui fait partie de l'ensemble Apache Commons. Elle fournit des implémentations améliorées et supplémentaires de collections (telles que les listes, les ensembles, les cartes, etc.) qui ne sont pas incluses dans la bibliothèque standard de Java. 
Elle a été intégrée dans `pom.xml`

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

        <!-- Dépendance commons-collections4 de Apache en version 4.0 -->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-collections4</artifactId>
            <version>4.0</version>
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
                <version>3.2.0</version>
                <configuration>
                    <configLocation>checkstyle.xml</configLocation>

                    <consoleOutput>true</consoleOutput>
                    <failsOnError>true</failsOnError>
                    <outputFile>target/checkstyle-result.xml</outputFile>
                </configuration>
            </plugin>
            <plugin>
                <groupId>com.github.bordertech.buildtools</groupId>
                <artifactId>badger</artifactId>
                <version>1.0.0</version>
                <executions>
                    <execution>
                        <id>verify</id>
                        <phase>verify</phase>
                        <goals>
                            <goal>badges</goal>
                        </goals>
                        <configuration>
                            <outputDir>${project.build.directory}/badges</outputDir>
                            <inputFiles>
                                <inputFile>target/checkstyle-result.xml</inputFile>
                            </inputFiles>
                        </configuration>
                    </execution>
                </executions>
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
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-javadoc-plugin</artifactId>
                <version>3.4.0</version>
                <executions>
                    <execution>
                        <id>generate-javadoc</id>
                        <goals>
                            <goal>javadoc</goal>
                        </goals>
                        <phase>verify</phase>
                        <configuration>
                            <reportOutputDirectory>${project.build.directory}/site/apidocs</reportOutputDirectory>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
        <sourceDirectory>src/main/java</sourceDirectory>
        <testSourceDirectory>src/test/java</testSourceDirectory>
    </build>
</project>

```





**Etape 1: nous allons étudier le code améliorée**

```java
package fr.univavignon.pokedex.imp;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections4.map.UnmodifiableMap;

import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.Pokemon;

public class RocketPokemonFactory implements IPokemonFactory {
	
	private static Map<Integer, String> index2name;
	static {
		Map<Integer, String> aMap = new HashMap<Integer, String>();
        aMap.put(-1, "Ash's Pikachu");
        aMap.put(0, "MISSINGNO");
        aMap.put(1, "Bulbasaur");
        //TODO : Gotta map them all !
        index2name = UnmodifiableMap.unmodifiableMap(aMap);
	}
	
	private static int generateRandomStat() {
		int total = 0;
		for(int i=0; i < 1000000; i++)
		{
			Random rn = new Random();
		    int r = rn.nextInt(2);
		    total = total + r;
		}
		return total / 10000;
	}

	@Override
	public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
		String name;
		if(!index2name.containsKey(index)) {
			name = index2name.get(0);
		} else {
			name = index2name.get(index);
		}
		int attack;
		int defense;
		int stamina;
		double iv;
		if(index < 0) {
			attack = 1000;
			defense = 1000;
			stamina = 1000;
			iv = 0;
		} else {
			attack = RocketPokemonFactory.generateRandomStat();
			defense = RocketPokemonFactory.generateRandomStat();
			stamina = RocketPokemonFactory.generateRandomStat();
			iv = 1;
		}
		return new Pokemon(index, name, attack, defense, stamina, cp, hp, dust, candy, iv);
	}

}
```

Explication du code:

```java
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
```

**HashMap** : Permet de stocker des paires clé-valeur, ici pour associer un index à un nom de Pokémon.

**Map** : Interface générale pour manipuler des structures de données sous forme clé-valeur.

**Random** : Générateur de nombres aléatoires, utilisé ici pour générer des statistiques aléatoires.



```java
import org.apache.commons.collections4.map.UnmodifiableMap;
```

Fournit une map qui ne peut pas être modifiée une fois créée. Cela protège les données contre les modifications accidentelles

.

```java
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.Pokemon;
```

**IPokemonFactory** : Interface que cette classe implémente pour créer des objets Pokémon.

**Pokemon** : Classe représentant un Pokémon avec ses attributs (attaque, défense, nom, etc.).



```java
public class RocketPokemonFactory implements IPokemonFactory {
```

Déclare la classe `RocketPokemonFactory` qui implémente l'interface `IPokemonFactory`. Elle doit fournir une implémentation pour la méthode `createPokemon`.



```java
private static Map<Integer, String> index2name;
```

Map statique pour associer des indices de Pokémon (clés) à leurs noms (valeurs). Elle est partagée entre toutes les instances de la classe.



```java
static {
	Map<Integer, String> aMap = new HashMap<>();
    aMap.put(-1, "Ash's Pikachu"); // Associe l'index -1 au nom "Ash's Pikachu".
    aMap.put(0, "MISSINGNO");      // Associe l'index 0 au nom "MISSINGNO".
    aMap.put(1, "Bulbasaur");      // Associe l'index 1 au nom "Bulbasaur".
    // TODO : Ajouter tous les Pokémon ("Gotta map them all!").
    index2name = UnmodifiableMap.unmodifiableMap(aMap);
}
```







