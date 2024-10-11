    package fr.univavignon.pokedex.api;

    import org.junit.*;
    import static org.junit.Assert.*;
    import org.mockito.Mockito;




    public class IPokemonMetadataProviderTest {

        private IPokemonMetadataProvider pokemonMetadataProvider;
        private PokemonMetadata bulbasaur;

        @Before
        public void initialisation() {
            // Crée le mock
            pokemonMetadataProvider = Mockito.mock(IPokemonMetadataProvider.class);
            // Crée un objet fictif pour le test
             bulbasaur = new PokemonMetadata(1, "Bulbasaur", 126, 126, 90);
        }

        @Test
        public void testGetPokemonMetadata() throws Exception {


            // Définir le comportement du mock
            Mockito.when(pokemonMetadataProvider.getPokemonMetadata(1)).thenReturn(bulbasaur);

            // Appeler la méthode testée
            PokemonMetadata result = pokemonMetadataProvider.getPokemonMetadata(1);

            // Vérifier les résultats avec des assertions
            assertEquals("Bulbasaur", result.getName());
            assertEquals(126, result.getAttack());
            assertEquals(126, result.getDefense());
            assertEquals(90, result.getStamina());

            // Vérifier que la méthode a été appelée une fois avec l'argument 1
            Mockito.verify(pokemonMetadataProvider).getPokemonMetadata(1);
        }

            @After
            public void nettoyer() throws Exception {
                bulbasaur = null;
            }
    }