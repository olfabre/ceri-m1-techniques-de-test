package fr.univavignon.pokedex.api;

public class PokemonTrainerFactoryImplementation implements IPokemonTrainerFactory {

    private IPokemonMetadataProvider metadataProvider;

    private IPokemonFactory pokemonFactory;

    public PokemonTrainerFactoryImplementation(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        this.metadataProvider = metadataProvider;
        this.pokemonFactory = pokemonFactory;
    }

    @Override
    public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
        IPokedex pokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
        return new PokemonTrainer(name, team, pokedex);
    }
}