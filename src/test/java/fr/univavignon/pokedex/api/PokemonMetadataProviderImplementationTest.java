package fr.univavignon.pokedex.api;

import org.junit.Test;
import static org.junit.Assert.*;

public class PokemonMetadataProviderImplementationTest {

    // Initialisation correcte de l'instance du fournisseur de métadonnées
    private final PokemonMetadataProviderImplementation pokemonMetadataProvider = PokemonMetadataProviderImplementation.getInstance();

    @Test
    public void getPokemonMetadataTest() {
        // Cas où l'index est invalide : négatif
        assertThrows(PokedexException.class, () -> pokemonMetadataProvider.getPokemonMetadata(-1));

        // Cas où l'index est invalide : au-delà des limites
        assertThrows(PokedexException.class, () -> pokemonMetadataProvider.getPokemonMetadata(152)); // Exemple avec un index supérieur au nombre total de Pokémon

        // Cas valide : Index 0 (Bulbizarre)
        try {
            PokemonMetadata pokemonMetadata = pokemonMetadataProvider.getPokemonMetadata(0);
            assertEquals(0, pokemonMetadata.getIndex());
            assertEquals("Bulbizarre", pokemonMetadata.getName());
            assertEquals(126, pokemonMetadata.getAttack());
            assertEquals(126, pokemonMetadata.getDefense());
            assertEquals(90, pokemonMetadata.getStamina());
        } catch (PokedexException e) {
            fail("Une exception ne devrait pas être levée pour un index valide (0)." + e.getMessage());
        }

        // Cas valide : Index 3 (Salamèche)
        try {
            PokemonMetadata pokemonMetadata = pokemonMetadataProvider.getPokemonMetadata(3);
            assertEquals(3, pokemonMetadata.getIndex());
            assertEquals("Salamèche", pokemonMetadata.getName());
            assertEquals(126, pokemonMetadata.getAttack());
            assertEquals(126, pokemonMetadata.getDefense());
            assertEquals(90, pokemonMetadata.getStamina());
        } catch (PokedexException e) {
            fail("Une exception ne devrait pas être levée pour un index valide (3)." + e.getMessage());
        }

        // Cas valide : Index 19 (Aquali)
        try {
            PokemonMetadata pokemonMetadata = pokemonMetadataProvider.getPokemonMetadata(19);
            assertEquals(19, pokemonMetadata.getIndex());
            assertEquals("Aquali", pokemonMetadata.getName());
            assertEquals(126, pokemonMetadata.getAttack());
            assertEquals(126, pokemonMetadata.getDefense());
            assertEquals(150, pokemonMetadata.getStamina());
        } catch (PokedexException e) {
            fail("Une exception ne devrait pas être levée pour un index valide (19)." + e.getMessage());
        }
    }


}
