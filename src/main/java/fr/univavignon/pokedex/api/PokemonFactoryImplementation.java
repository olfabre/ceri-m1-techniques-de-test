package fr.univavignon.pokedex.api;


public class PokemonFactoryImplementation implements IPokemonFactory {

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        // Création d'un nouveau Pokémon
        Pokemon pokemon = new Pokemon(index, "Nom du Pokémon", 10, 10, 10, cp, hp, dust, candy, 0.0);
        return pokemon;
    }
}