package fr.univavignon.pokedex.api;

import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonFactoryImplementation;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PokemonFactoryImplementationTest {

    private IPokemonFactory pokemonFactory;

    @Before
    public void setUp() {
        pokemonFactory = new PokemonFactoryImplementation();
    }

    @Test
    public void testCreatePokemon() {
        // Création d'un nouveau Pokémon
        Pokemon pokemon = pokemonFactory.createPokemon(1, 100, 100, 100, 100);

        // Vérification des propriétés du Pokémon
        assertEquals(1, pokemon.getIndex());
        assertEquals("Nom du Pokémon", pokemon.getName());
        assertEquals(10, pokemon.getAttack());
        assertEquals(10, pokemon.getDefense());
        assertEquals(10, pokemon.getStamina());
        assertEquals(100, pokemon.getCp());
        assertEquals(100, pokemon.getHp());
        assertEquals(100, pokemon.getDust());
        assertEquals(100, pokemon.getCandy());
        assertEquals(0.0, pokemon.getIv(), 0.01);
    }

    @Test
    public void testCreatePokemonWithInvalidIndex() {
        // Création d'un nouveau Pokémon avec un index invalide
        Pokemon pokemon = pokemonFactory.createPokemon(-1, 100, 100, 100, 100);

        // Vérification des propriétés du Pokémon
        assertEquals(-1, pokemon.getIndex());
    }

    @Test
    public void testCreatePokemonWithInvalidCp() {
        // Création d'un nouveau Pokémon avec un cp invalide
        Pokemon pokemon = pokemonFactory.createPokemon(1, -100, 100, 100, 100);

        // Vérification des propriétés du Pokémon
        assertEquals(1, pokemon.getIndex());
        assertEquals(-100, pokemon.getCp());
    }
}