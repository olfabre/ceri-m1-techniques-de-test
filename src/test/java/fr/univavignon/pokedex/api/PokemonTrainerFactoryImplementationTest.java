package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PokemonTrainerFactoryImplementationTest {

    private IPokemonMetadataProvider metadataProvider;
    private IPokemonFactory pokemonFactory;
    private IPokedexFactory pokedexFactory;
    private IPokedex pokedex;

    private PokemonTrainerFactoryImplementation trainerFactory;

    @Before
    public void setUp() {
        // Mock des dépendances
        metadataProvider = mock(IPokemonMetadataProvider.class);
        pokemonFactory = mock(IPokemonFactory.class);
        pokedexFactory = mock(IPokedexFactory.class);
        pokedex = mock(IPokedex.class);

        // Comportement du mock pour la méthode createPokedex
        when(pokedexFactory.createPokedex(metadataProvider, pokemonFactory)).thenReturn(pokedex);

        // Initialisation de la factory
        trainerFactory = new PokemonTrainerFactoryImplementation(metadataProvider, pokemonFactory);
    }

    @Test
    public void testCreateTrainer() {
        // Données d'entrée
        String trainerName = "Ash";
        Team team = Team.MYSTIC;

        // Appel de la méthode
        PokemonTrainer trainer = trainerFactory.createTrainer(trainerName, team, pokedexFactory);

        // Vérifications
        assertNotNull("Le trainer ne doit pas être null", trainer);
        assertEquals("Le nom du trainer doit correspondre", trainerName, trainer.getName());
        assertEquals("L'équipe du trainer doit correspondre", team, trainer.getTeam());
        assertEquals("Le pokédex du trainer doit être celui créé par le pokedexFactory", pokedex, trainer.getPokedex());

        // Vérification des interactions avec les mocks
        verify(pokedexFactory).createPokedex(metadataProvider, pokemonFactory);
        verifyNoMoreInteractions(pokedexFactory, metadataProvider, pokemonFactory);
    }
}
