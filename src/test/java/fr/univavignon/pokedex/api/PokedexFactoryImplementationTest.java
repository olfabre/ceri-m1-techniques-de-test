package fr.univavignon.pokedex.api;

import org.junit.*;
import static org.junit.Assert.*;
import org.mockito.Mockito;

public class PokedexFactoryImplementationTest {

    private IPokedexFactory pokedexFactory;
    private IPokemonMetadataProvider metadataProviderMock;
    private IPokemonFactory pokemonFactoryMock;


    @Before
    public void initialiser() throws Exception {
        // Initialisation de la fabrique de Pokedex
        pokedexFactory = new PokedexFactoryImplementation();

        // Création des mocks pour les classes dont nous avons besoin
        metadataProviderMock = Mockito.mock(IPokemonMetadataProvider.class);
        pokemonFactoryMock = Mockito.mock(IPokemonFactory.class);
    }

    @Test
    public void testCreatePokedex() {
        // Création du Pokedex avec les mocks
        IPokedex pokedex = pokedexFactory.createPokedex(metadataProviderMock, pokemonFactoryMock);

        // Vérification que le Pokedex créé n'est pas nul
        assertNotNull("L'instance de Pokedex ne doit pas être nulle", pokedex);

        // Vérification que l'instance de Pokedex est bien de la classe attendue
        assertTrue("L'instance doit être de type PokedexImplementation", pokedex instanceof PokedexImplementation);
    }

    @Test
    public void testPokedexUsesMetadataProvider() throws PokedexException {

        // Préparation du mock pour retourner un PokemonMetadata spécifique
        PokemonMetadata metadata = new PokemonMetadata(0, "olivier", 126, 126, 90);
        Mockito.when(metadataProviderMock.getPokemonMetadata(0)).thenReturn(metadata);

        // Création du Pokedex
        IPokedex pokedex = pokedexFactory.createPokedex(metadataProviderMock, pokemonFactoryMock);

        // Vérification que getPokemonMetadata utilise bien le fournisseur de métadonnées
        PokemonMetadata result = pokedex.getPokemonMetadata(0);
        assertEquals(metadata.getIndex(), result.getIndex());
        assertEquals(metadata.getName(), result.getName());
        assertEquals(metadata.getAttack(), result.getAttack());
        assertEquals(metadata.getDefense(), result.getDefense());
        assertEquals(metadata.getStamina(), result.getStamina());

        // Vérifie que la méthode du mock a été appelée
        Mockito.verify(metadataProviderMock).getPokemonMetadata(0);
    }

    @Test
    public void testPokedexUsesPokemonFactory() {

        // Préparation du mock pour retourner un Pokemon spécifique
        Pokemon pokemon = new Pokemon(0, "olivier", 126, 126, 90, 613, 64, 4000, 3, 56.0);
        Mockito.when(pokemonFactoryMock.createPokemon(0, 613, 64, 4000, 3)).thenReturn(pokemon);

        // Création du Pokedex
        IPokedex pokedex = pokedexFactory.createPokedex(metadataProviderMock, pokemonFactoryMock);

        // Vérification que createPokemon utilise bien la fabrique de Pokémon
        Pokemon result = pokedex.createPokemon(0, 100, 64, 4000, 3);
        assertEquals(pokemon.getIndex(), result.getIndex());
        assertEquals(pokemon.getName(), result.getName());
        assertEquals(pokemon.getAttack(), result.getAttack());
        assertEquals(pokemon.getDefense(), result.getDefense());
        assertEquals(pokemon.getStamina(), result.getStamina());
        assertEquals(pokemon.getCp(), result.getCp());
        assertEquals(pokemon.getHp(), result.getHp());
        assertEquals(pokemon.getDust(), result.getDust());
        assertEquals(pokemon.getCandy(), result.getCandy());
        assertEquals(pokemon.getIv(), result.getIv(), 0.001);

        // Vérifie que la méthode du mock a été appelée
        Mockito.verify(pokemonFactoryMock).createPokemon(0, 613, 64, 4000, 3);
    }




    @After
    public void nettoyer() throws Exception {
    }
}