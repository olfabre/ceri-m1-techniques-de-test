package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PokemonFactoryImplementationTest {

    private IPokemonFactory implementationFactory;
    private IPokemonFactory rocketFactory;

    @Before
    public void setUp() {
        implementationFactory = new PokemonFactoryImplementation();
        rocketFactory = new RocketPokemonFactory();
    }

    private void testCreatePokemon(IPokemonFactory factory) {
        // Création d'un nouveau Pokémon
        Pokemon pokemon = factory.createPokemon(1, 100, 100, 100, 100);

        // Vérification des propriétés du Pokémon
        assertEquals(1, pokemon.getIndex());
        assertNotNull(pokemon.getName());
        assertTrue(pokemon.getAttack() >= 0);
        assertTrue(pokemon.getDefense() >= 0);
        assertTrue(pokemon.getStamina() >= 0);
        assertEquals(100, pokemon.getCp());
        assertEquals(100, pokemon.getHp());
        assertEquals(100, pokemon.getDust());
        assertEquals(100, pokemon.getCandy());
        assertTrue(pokemon.getIv() >= 0.0 && pokemon.getIv() <= 1.0);
    }

    private void testInvalidIndex(IPokemonFactory factory) {
        // Création d'un Pokémon avec un index invalide
        Pokemon pokemon = factory.createPokemon(-1, 100, 100, 100, 100);

        // Vérification des propriétés du Pokémon
        assertEquals(-1, pokemon.getIndex());
        assertNotNull(pokemon.getName());
        if (factory instanceof RocketPokemonFactory) {
            assertEquals("Ash's Pikachu", pokemon.getName());
        }
    }

    private void testInvalidCp(IPokemonFactory factory) {
        // Création d'un Pokémon avec un cp invalide
        Pokemon pokemon = factory.createPokemon(1, -100, 100, 100, 100);

        // Vérification des propriétés du Pokémon
        assertEquals(1, pokemon.getIndex());
        assertEquals(-100, pokemon.getCp());
    }

    @Test
    public void testImplementationFactory() {
        testCreatePokemon(implementationFactory);
        testInvalidIndex(implementationFactory);
        testInvalidCp(implementationFactory);
    }

    @Test
    public void testRocketFactory() {
        testCreatePokemon(rocketFactory);
        testInvalidIndex(rocketFactory);
        testInvalidCp(rocketFactory);
    }
}
