package fr.univavignon.pokedex.api;

import java.util.Comparator;
import java.util.List;

/**
 * Interface IPokedex. Un IPokedex a pour but de stocker toutes les informations concernant
 * les pokémons capturés, ainsi que leurs métadonnées par défaut.
 */
public interface IPokedex extends IPokemonMetadataProvider, IPokemonFactory {




	/**
	 * Renvoie le nombre de pokémons que contient ce pokedex
	 *
	 * @return Nombre de pokémons dans ce pokedex.
	 */
	int size();





	/**
	 * Ajoute le pokémon donné à ce pokedex et renvoie son index unique.
	 * 
	 * @param pokemon Pokemon à ajouter à ce pokedex.
	 * @return Index de ce pokémon par rapport à ce pokedex
	 */
	int addPokemon(Pokemon pokemon);






	/**
	 * Localise le pokémon identifié par le id donné.
	 * 
	 * @param id Identifiant relatif unique du pokedex.
	 * @return Pokemon désigné par l'identifiant donné.
	 * @throws PokedexException Si le index donné n'est pas valide.
	 */
	Pokemon getPokemon(int id) throws PokedexException;





	/**
	 * Retourne une liste non modifiable de tous les pokémons que ce pokedex contient.
	 * La liste sera triée en utilisant l'ordre donné.
	 * 
	 * @return Liste non modifiable de tous les pokémons.
	 */
	List<Pokemon> getPokemons();





	/**
	 * Retourne une liste non modifiable de tous les pokémons que ce pokedex contient.
	 * La liste sera triée en utilisant l'ordre donné.
	 * 
	 * @param order Instance de comparateur utilisée pour trier la vue créée.
	 * @return Liste triée et non modifiable de tous les pokémons.
	 */
	List<Pokemon> getPokemons(Comparator<Pokemon> order);
	
}
