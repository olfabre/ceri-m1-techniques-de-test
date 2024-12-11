# TP6 : Ils sont de retour pour vous jouer un mauvais tour



## Épisode 1 - Giovanni's touch



Une implémentation de `IPokemonFactory`  a été remise et qui est meilleur que la notre.

On doit intégrer cette implémentation dans notre projet et la passez au crible grâce à notre suite de tests et au travers d'une revue de code, on doit rédigez un succint rapport qui présente nos conclusions. Certains défauts de leur implémentation ne sont peut-être pas couverts par les tests que nous avons déjà mis en place, on ne doit pas hésitez pas à relever ces défauts aussi, et, si nous y parvenons, à mettre en place des tests pour les détecter automatiquement.

**Etape 1: Nous allons étudier le code**

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







