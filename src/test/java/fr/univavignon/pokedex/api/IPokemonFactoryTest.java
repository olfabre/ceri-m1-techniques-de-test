package fr.univavignon.pokedex.api;

import org.junit.*;
import static org.junit.Assert.*;
import org.mockito.Mockito;

public class IPokemonFactoryTest {

    private IPokemonFactory pokemonFactory;
    private Pokemon Bulbizarre;

    @Before
    public void initialiser() {
        // Crée un mock pour l'usine de création de Pokemon
        pokemonFactory = Mockito.mock(IPokemonFactory.class);

        // Crée une instance fictive de Pokemon pour le test
        Bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0);

        // Définir le comportement du mock pour créer le Pokemon
        Mockito.when(pokemonFactory.createPokemon(0, 613, 64, 4000, 4)).thenReturn(Bulbizarre);
    }

    @Test
    public void testCreatePokemon() {
        // Appeler la méthode testée
        Pokemon result = pokemonFactory.createPokemon(0, 613, 64, 4000, 4);

        // Vérifier que les valeurs du Pokemon sont correctes
        assertEquals(0, result.getIndex());
        assertEquals("Bulbizarre", result.getName());
        assertEquals(126, result.getAttack());
        assertEquals(126, result.getDefense());
        assertEquals(90, result.getStamina());
        assertEquals(613, result.getCp());
        assertEquals(64, result.getHp());
        assertEquals(4000, result.getDust());
        assertEquals(4, result.getCandy());
        assertEquals(56.0, result.getIv(), 0.01);

        // Vérifie que la méthode createPokemon a été appelée une fois avec les bons paramètres
        Mockito.verify(pokemonFactory).createPokemon(0, 613, 64, 4000, 4);
    }

    @After
    public void nettoyer() {
        Bulbizarre = null;
    }
}
