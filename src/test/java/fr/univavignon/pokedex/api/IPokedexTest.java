package fr.univavignon.pokedex.api;

import org.junit.*;
import static org.junit.Assert.*;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IPokedexTest {

    private IPokedex pokedex;
    private Pokemon bulbizarre;
    private Pokemon aquali;

    @Before
    public void initialiser() throws PokedexException {
        // Créer un mock pour l'interface IPokedex
        pokedex = Mockito.mock(IPokedex.class);

        // Créer des objets Pokémon pour les tests
        bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0);
        aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100.0);

        // Définir le comportement pour la méthode getPokemons
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(bulbizarre);
        pokemons.add(aquali);
        Mockito.when(pokedex.getPokemons()).thenReturn(pokemons);

        // Définir le comportement du mock pour la méthode addPokemon
        Mockito.when(pokedex.addPokemon(bulbizarre)).thenReturn(0);
        Mockito.when(pokedex.addPokemon(aquali)).thenReturn(133);

        // Définir le comportement du mock pour la méthode size
        Mockito.when(pokedex.size()).thenReturn(2);

        // Définir le comportement du mock pour la méthode getPokemon
        Mockito.when(pokedex.getPokemon(0)).thenReturn(bulbizarre);
        Mockito.when(pokedex.getPokemon(133)).thenReturn(aquali);
    }

    @Test
    public void testAddPokemon() {
        // Ajouter des pokemons et vérifier les indices retournés
        int indexBulbizarre = pokedex.addPokemon(bulbizarre);
        int indexAquali = pokedex.addPokemon(aquali);

        assertEquals(0, indexBulbizarre);
        assertEquals(133, indexAquali); // Correction ici
    }

    @Test
    public void testSize() {
        // Vérifier que la taille du pokédex est correcte
        assertEquals(2, pokedex.size());
    }

    @Test
    public void testGetPokemon() throws PokedexException {
        // Récupérer des pokemons par leur index et vérifier leurs données
        Pokemon resultatBulbizarre = pokedex.getPokemon(0);
        Pokemon resultatAquali = pokedex.getPokemon(133); // Correction ici

        assertEquals(bulbizarre, resultatBulbizarre);
        assertEquals(aquali, resultatAquali);
    }

    @Test
    public void testGetPokemons() {
        // Vérifier que la liste des pokemons est correcte
        List<Pokemon> pokemons = pokedex.getPokemons();
        assertNotNull(pokemons);
        assertEquals(2, pokemons.size());
        assertEquals(bulbizarre, pokemons.get(0));
        assertEquals(aquali, pokemons.get(1));
    }

    @Test
    public void testGetPokemonsSorted() {
        // Créer un comparateur pour trier les pokemons par index
        Comparator<Pokemon> comparator = Comparator.comparingInt(Pokemon::getIndex);

        // Définir le comportement pour getPokemons avec un tri
        List<Pokemon> sortedPokemons = new ArrayList<>();
        sortedPokemons.add(bulbizarre);
        sortedPokemons.add(aquali);
        Mockito.when(pokedex.getPokemons(comparator)).thenReturn(sortedPokemons);

        // Vérifier que la liste triée est correcte
        List<Pokemon> pokemons = pokedex.getPokemons(comparator);
        assertEquals(bulbizarre, pokemons.get(0));
        assertEquals(aquali, pokemons.get(1));
    }

    @After
    public void nettoyer() {
        bulbizarre = null;
        aquali = null;
    }
}
