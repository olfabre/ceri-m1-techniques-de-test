package fr.univavignon.pokedex.api;

import org.junit.*;
import static org.junit.Assert.*;
import org.mockito.Mockito;

public class IPokemonTrainerFactoryTest {

    private IPokemonTrainerFactory pokemonTrainerFactory; // mock pour tester l'interface
    private IPokedexFactory pokedexFactory; // // mock pour isoler l'inetrface
    private IPokedex pokedex; // mock pour isoler l'interface

    @Before
    public void initialiser() {
        // Créer un mock pour IPokemonTrainerFactory
        pokemonTrainerFactory = Mockito.mock(IPokemonTrainerFactory.class);

        // Créer des mocks pour les dépendances
        pokedexFactory = Mockito.mock(IPokedexFactory.class);
        pokedex = Mockito.mock(IPokedex.class);

        // Définir le comportement de la fabrique de pokédex
        Mockito.when(pokedexFactory.createPokedex(Mockito.any(), Mockito.any())).thenReturn(pokedex);

        // Simuler la création d'un PokemonTrainer
        PokemonTrainer trainer = new PokemonTrainer("Ash", Team.VALOR, pokedex);
        Mockito.when(pokemonTrainerFactory.createTrainer("Ash", Team.VALOR, pokedexFactory)).thenReturn(trainer);
    }

    @Test
    public void testCreateTrainer() {
        // Créer un dresseur via la fabrique
        PokemonTrainer trainer = pokemonTrainerFactory.createTrainer("Ash", Team.VALOR, pokedexFactory);

        // Vérifier que le nom, l'équipe et le pokédex sont corrects
        assertEquals("Ash", trainer.getName());
        assertEquals(Team.VALOR, trainer.getTeam());
        assertEquals(pokedex, trainer.getPokedex());
    }

    @After
    public void nettoyer() {
        // Libérer les ressources
        pokemonTrainerFactory = null;
        pokedexFactory = null;
        pokedex = null;
    }
}
