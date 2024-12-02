package fr.univavignon.pokedex.api;

/**
 * Implementation de la factory pour créer des entraîneurs de Pokémon.
 */
public class PokemonTrainerFactoryImplementation implements IPokemonTrainerFactory {

    /**
     * Fournisseur de métadonnées pour les Pokémon.
     */
    private IPokemonMetadataProvider metadataProvider;

    /**
     * Factory pour créer des Pokémon.
     */
    private IPokemonFactory pokemonFactory;

    /**
     * Constructeur de la factory.
     *
     * @param metadataProvider fournisseur de métadonnées pour les Pokémon
     * @param pokemonFactory factory pour créer des Pokémon
     */
    public PokemonTrainerFactoryImplementation(IPokemonMetadataProvider metadataProvider,
                                               IPokemonFactory pokemonFactory) {
        this.metadataProvider = metadataProvider;
        this.pokemonFactory = pokemonFactory;
    }

    /**
     * Crée un nouvel entraîneur de Pokémon.
     *
     * @param name nom de l'entraîneur
     * @param team équipe de l'entraîneur
     * @param pokedexFactory factory pour créer un Pokédex
     * @return l'entraîneur de Pokémon créé
     */
    @Override
    public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
        IPokedex pokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
        return new PokemonTrainer(name, team, pokedex);
    }
}