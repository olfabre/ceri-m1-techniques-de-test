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
        assertEquals(0, pokedex.size());
        pokedex.addPokemon(pokemon);
        assertEquals(1, pokedex.size());
    }

    @Test
    public void testAddPokemon() {
        int index = pokedex.addPokemon(pokemon);
        assertEquals(0, index);
        assertEquals(1, pokedex.size());
    }

    @Test
    public void testGetPokemon() throws PokedexException {
        pokedex.addPokemon(pokemon);
        Pokemon retrievedPokemon = pokedex.getPokemon(0);
        assertEquals(pokemon, retrievedPokemon);

        assertThrows(PokedexException.class, () -> pokedex.getPokemon(1));
    }


    @Test
    public void testGetPokemons() {
        pokedex.addPokemon(pokemon);
        List<Pokemon> pokemons = pokedex.getPokemons();
        assertEquals(1, pokemons.size());
        assertEquals(pokemon, pokemons.get(0));
    }

    @Test
    public void testGetPokemonsWithOrder() {
        Pokemon pokemon2 = new Pokemon(1, "Bulbasaur", 118, 118, 90, 300, 45, 3000, 3, 45.0);
        pokedex.addPokemon(pokemon);
        pokedex.addPokemon(pokemon2);

        List<Pokemon> sortedPokemons = pokedex.getPokemons(Comparator.comparing(Pokemon::getCp).reversed());
        assertEquals(pokemon, sortedPokemons.get(0));
        assertEquals(pokemon2, sortedPokemons.get(1));
    }

    @Test
    public void testCreatePokemon() {
        Pokemon createdPokemon = pokedex.createPokemon(0, 500, 60, 4000, 3);
        assertNotNull(createdPokemon);
        assertEquals(0, createdPokemon.getIndex());
        assertEquals(500, createdPokemon.getCp());
    }

    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        PokemonMetadata metadata = pokedex.getPokemonMetadata(0);
        assertNotNull(metadata);
        assertEquals(0, metadata.getIndex());
        assertEquals("olivier", metadata.getName());
        assertThrows(PokedexException.class, () -> pokedex.getPokemonMetadata(150));
    }

    @After
    public void nettoyer() {
        pokedex = null;
        pokemon = null;
    }


}