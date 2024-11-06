package fr.univavignon.pokedex.api;

import java.util.Comparator;
import java.util.List;



public class PokedexImplementation implements IPokedex {

    // Constructeur
    public void PokedexImplentation() {



    }


    @Override
    public int size() {
        return 0;
    }

    @Override
    public int addPokemon(Pokemon pokemon) {
        return 0;
    }

    @Override
    public Pokemon getPokemon(int id) throws PokedexException {
        return null;
    }

    @Override
    public List<Pokemon> getPokemons() {
        return List.of();
    }

    @Override
    public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
        return List.of();
    }

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        return null;
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        return null;
    }
}
