package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class PokedexImplementation implements IPokedex {

    // Liste pour stocker les Pokémon ajoutés au Pokédex
    private List<Pokemon> pokemons;
    private IPokemonMetadataProvider metadataProvider;
    private IPokemonFactory pokemonFactory;


    // Constructeur
    public PokedexImplementation() {
        this.pokemons = new ArrayList<>();
    }

    public PokedexImplementation(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        this.metadataProvider = metadataProvider;
        this.pokemonFactory = pokemonFactory;
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



    /**
     * Retourne une liste non modifiable de tous les Pokémon dans le Pokédex.
     *
     * @return Liste non modifiable de tous les Pokémon.
     */
    @Override
    public List<Pokemon> getPokemons() {
        return Collections.unmodifiableList(pokemons);
    }



    /**
     * Retourne une liste triée de tous les Pokémon dans le Pokédex en utilisant
     * un comparateur spécifique.
     *
     * @param order Comparateur pour le tri.
     * @return Liste triée et non modifiable de tous les Pokémon.
     */
    @Override
    public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
        List<Pokemon> sortedPokemons = new ArrayList<>(pokemons);
        sortedPokemons.sort(order);
        return Collections.unmodifiableList(sortedPokemons);
    }



    /**
     * Crée une instance de Pokémon en utilisant les paramètres fournis.
     *
     * @param index Index du Pokémon.
     * @param cp Points de combat du Pokémon.
     * @param hp Points de vie du Pokémon.
     * @param dust Poussière requise pour améliorer le Pokémon.
     * @param candy Bonbons requis pour améliorer le Pokémon.
     * @return Instance de Pokémon créée avec les valeurs fournies.
     */
    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {

            double iv = 50.0; // Par exemple, valeur de perfection IV par défaut
            return new Pokemon(index, "olivier", 100, 100, 100, cp, hp, dust, candy, iv);
    }


    /**
     * Récupère et retourne les métadonnées du Pokémon correspondant à l'index fourni.
     *
     * @param index Index du Pokémon dont les métadonnées doivent être récupérées.
     * @return Métadonnées du Pokémon.
     * @throws PokedexException Si l'index donné n'est pas valide.
     */
    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        if (index < 0 || index >= 150) { // Supposons un maximum de 150 Pokémon
            throw new PokedexException("Index de Pokémon invalide.");
        }
        return new PokemonMetadata(index, "olivier", 100, 100, 100);
    }
}