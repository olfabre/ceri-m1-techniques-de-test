package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.mockito.*;

public class PokedexFactoryImplementationTest {

    private IPokedexFactory pokedexFactory;
    private IPokemonMetadataProvider metadataProviderMock;
    private IPokemonFactory pokemonFactoryMock;

    @Before
    public void initialiser() {
        MockitoAnnotations.initMocks(this);  // Initialisation des mocks

        pokedexFactory = new PokedexFactoryImplementation();
        metadataProviderMock = Mockito.mock(IPokemonMetadataProvider.class);
        pokemonFactoryMock = Mockito.mock(IPokemonFactory.class);

    }

    @Test
    public void testCreatePokedex() {
        IPokedex pokedex = pokedexFactory.createPokedex(metadataProviderMock, pokemonFactoryMock);
        assertNotNull(pokedex);
        assertTrue(pokedex instanceof PokedexImplementation);
    }



    @After
    public void nettoyer() {
        pokedexFactory = null;
        metadataProviderMock = null;
        pokemonFactoryMock = null;
    }
}
