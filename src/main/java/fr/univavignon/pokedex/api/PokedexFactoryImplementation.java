package fr.univavignon.pokedex.api;

public class PokedexFactoryImplementation implements IPokedexFactory{

    @Override
    public IPokedex createPokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        // On retourne une instance de PokedexImplementation en utilisant les fournisseurs fournis.
        return new PokedexImplementation(metadataProvider, pokemonFactory);
    }
}
