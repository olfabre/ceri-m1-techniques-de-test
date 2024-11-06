package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;



public class PokedexImplementation implements IPokedex {

    // Liste pour stocker les Pokémon ajoutés au Pokédex
    private List<Pokemon> pokemons;



    // Constructeur
    public PokedexImplementation() {
        this.pokemons = new ArrayList<>();
    }


    /**
     * Retourne le nombre de Pokémon dans ce Pokédex.
     *
     * @return Nombre de Pokémon dans ce Pokédex.
     */
    @Override
    public int size() {
        return pokemons.size();
    }


    /**
     * Ajoute un Pokémon au Pokédex et retourne son index unique.
     *
     * @param pokemon Pokémon à ajouter.
     * @return Index de ce Pokémon dans le Pokédex.
     */
    @Override
    public int addPokemon(Pokemon pokemon) {
        pokemons.add(pokemon);
        return pokemons.size() - 1; // Retourne l'index du dernier Pokémon ajouté (indice qui commence à zéro)
    }




    /**
     * Récupère un Pokémon par son ID.
     *
     * @param id Identifiant unique dans le Pokédex.
     * @return Pokémon correspondant à cet ID.
     * @throws PokedexException Si l'ID n'est pas valide.
     */
    @Override
    public Pokemon getPokemon(int id) throws PokedexException {
        if (id < 0 || id >= pokemons.size()) {
            throw new PokedexException("ID de Pokémon invalide.");
        }
        return pokemons.get(id);
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