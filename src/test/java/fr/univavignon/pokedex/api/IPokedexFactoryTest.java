package fr.univavignon.pokedex.api;

import org.junit.*;
import static org.junit.Assert.*;
import org.mockito.Mockito;

public class IPokedexFactoryTest {

    private IPokedexFactory pokedexFactory; // un mock pour la classe que l'on veut tester
    private IPokemonMetadataProvider metadataProvider; // un mock pour isoler l'interface
    private IPokemonFactory pokemonFactory; // un mock pour isoler l'interface
    private IPokedex pokedex; // un mock pour simuler l'objet retourné par la méthode  createPokedex()

    @Before
    public void initialiser() {
        // Créer des mocks pour IPokemonMetadataProvider et IPokemonFactory
        metadataProvider = Mockito.mock(IPokemonMetadataProvider.class);
        pokemonFactory = Mockito.mock(IPokemonFactory.class);

        // Créer un mock pour IPokedexFactory
        pokedexFactory = Mockito.mock(IPokedexFactory.class);

        // Créer un mock pour IPokedex
        pokedex = Mockito.mock(IPokedex.class);

        // Définir le comportement du mock pokedexFactory
        Mockito.when(pokedexFactory.createPokedex(metadataProvider, pokemonFactory)).thenReturn(pokedex);
    }

    @Test
    public void testCreatePokedex() {
        // Appeler la méthode testée
        IPokedex createdPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        // Vérifier que l'objet retourné n'est pas null
        assertNotNull(createdPokedex);

        // Vérifier que le pokedex retourné est bien celui attendu
        assertEquals(pokedex, createdPokedex);

        // Vérifier que la méthode createPokedex a bien été appelée avec les bons arguments
        Mockito.verify(pokedexFactory).createPokedex(metadataProvider, pokemonFactory);
    }

    @After
    public void nettoyer() {
        pokedex = null;
        metadataProvider = null;
        pokemonFactory = null;
    }
}
