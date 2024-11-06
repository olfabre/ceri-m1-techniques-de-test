	package fr.univavignon.pokedex.api;

	/**
	 * Interface d'usine pour la classe qui vise à créer une instance d'IPokedex.
	 */
	public interface IPokedexFactory {

		/**
		 * Crée une nouvelle instance de pokedex en utilisant le metadataProvider et le pokemonFactory donnés.
		 *
		 * @param metadataProvider Fournisseur de métadonnées que le pokedex créé utilisera.
		 * @param pokemonFactory Fabrique de pokémons que le pokedex créé utilisera.
		 * @return Instance de pokedex créée.
		 */
		IPokedex createPokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory);

	}
