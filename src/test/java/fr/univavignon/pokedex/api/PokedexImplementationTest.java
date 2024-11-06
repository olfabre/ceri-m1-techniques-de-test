package fr.univavignon.pokedex.api;

import org.junit.*;
import static org.junit.Assert.*;
import org.mockito.Mockito;

import java.util.Comparator;
import java.util.List;



public class PokedexImplementationTest {

    private PokedexImplementation pokedex;
    private Pokemon pokemon;

    @Before
    public void initialiser() {
        pokedex = new PokedexImplementation();
        pokemon = new Pokemon(0, "Pikachu", 126, 126, 90, 500, 60, 4000, 3, 56.0);
    }

    @Test
    public void testSize() {
        assertEquals(0, pokedex.size(), "Le Pokédex doit être vide au début.");
        pokedex.addPokemon(pokemon);
        assertEquals(1, pokedex.size(), "Le Pokédex doit contenir un Pokémon après l'ajout.");
    }

    @Test
    public void testAddPokemon() {
        int index = pokedex.addPokemon(pokemon);
        assertEquals(0, index, "L'index du premier Pokémon ajouté devrait être 0.");
        assertEquals(1, pokedex.size(), "La taille du Pokédex devrait être de 1 après l'ajout d'un Pokémon.");
    }

    @Test
    public void testGetPokemon() throws PokedexException {
        pokedex.addPokemon(pokemon);
        Pokemon retrievedPokemon = pokedex.getPokemon(0);
        assertEquals(pokemon, retrievedPokemon, "Le Pokémon récupéré doit être le même que celui ajouté.");

        assertThrows(PokedexException.class, () -> pokedex.getPokemon(1), "Une exception devrait être levée pour un ID invalide.");
    }

    @Test
    public void testGetPokemons() {
        pokedex.addPokemon(pokemon);
        List<Pokemon> pokemons = pokedex.getPokemons();
        assertEquals(1, pokemons.size(), "La liste devrait contenir un Pokémon.");
        assertEquals(pokemon, pokemons.get(0), "Le Pokémon de la liste devrait correspondre à celui ajouté.");
    }

    @Test
    public void testGetPokemonsWithOrder() {
        Pokemon pokemon2 = new Pokemon(1, "Bulbasaur", 118, 118, 90, 300, 45, 3000, 3, 45.0);
        pokedex.addPokemon(pokemon);
        pokedex.addPokemon(pokemon2);

        List<Pokemon> sortedPokemons = pokedex.getPokemons(Comparator.comparing(Pokemon::getCp).reversed());
        assertEquals(pokemon, sortedPokemons.get(0), "Le Pokémon avec le CP le plus élevé devrait être en premier.");
        assertEquals(pokemon2, sortedPokemons.get(1), "Le Pokémon avec le CP le plus bas devrait être en dernier.");
    }

    @Test
    public void testCreatePokemon() {
        Pokemon createdPokemon = pokedex.createPokemon(0, 500, 60, 4000, 3);
        assertNotNull(createdPokemon, "Le Pokémon créé ne doit pas être null.");
        assertEquals(0, createdPokemon.getIndex(), "L'index doit correspondre à celui fourni.");
        assertEquals(500, createdPokemon.getCp(), "Les points de combat (CP) doivent correspondre.");
    }

    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        PokemonMetadata metadata = pokedex.getPokemonMetadata(0);
        assertNotNull(metadata, "Les métadonnées ne doivent pas être nulles.");
        assertEquals(0, metadata.getIndex(), "L'index des métadonnées doit être correct.");
        assertEquals("olivier", metadata.getName(), "Le nom doit correspondre à celui défini.");
        assertThrows(PokedexException.class, () -> pokedex.getPokemonMetadata(150), "Une exception doit être levée pour un index invalide.");
    }
}