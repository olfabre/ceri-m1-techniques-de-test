package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation de la classe permettant de fournir les métadonnées des Pokémon.
 * Cette classe suit le modèle Singleton pour garantir une seule instance.
 */
public class PokemonMetadataProviderImplementation {

    /** Liste des métadonnées des Pokémon. */
    private List<PokemonMetadata> pokemonMetadatas;

    /** Instance unique de la classe (Singleton). */
    private static PokemonMetadataProviderImplementation instance;

    /**
     * Retourne l'instance unique de la classe {@link PokemonMetadataProviderImplementation}.
     * Si l'instance n'existe pas encore, elle est créée.
     *
     * @return L'instance unique de {@link PokemonMetadataProviderImplementation}.
     */
    public static PokemonMetadataProviderImplementation getInstance() {
        if (instance == null) {
            instance = new PokemonMetadataProviderImplementation();
        }
        return instance;
    }

    /**
     * Constructeur privé pour empêcher l'instanciation directe de la classe.
     * Initialise la liste des métadonnées des Pokémon.
     */
    private PokemonMetadataProviderImplementation() {
        this.pokemonMetadatas = new ArrayList<>();
        initializePokemonMetadata();
    }

    /**
     * Initialise les métadonnées des Pokémon prédéfinis.
     * Cette méthode ajoute plusieurs Pokémon avec leurs statistiques de base.
     */
    private void initializePokemonMetadata() {
        PokemonMetadata bulbizarre = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        PokemonMetadata salamiche = new PokemonMetadata(3, "Salamèche", 126, 126, 90);
        PokemonMetadata aquali = new PokemonMetadata(19, "Aquali", 126, 126, 150);

        pokemonMetadatas.add(bulbizarre);
        pokemonMetadatas.add(salamiche);
        pokemonMetadatas.add(aquali);
    }


    /**
     * Retourne les métadonnées d'un Pokémon en fonction de son index.
     *
     * @param index L'index du Pokémon.
     * @return Les métadonnées du Pokémon correspondant.
     * @throws PokedexException Si aucun Pokémon n'existe pour l'index spécifié.
     */
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        for (PokemonMetadata pokemonMetadata : pokemonMetadatas) {
            if (pokemonMetadata.getIndex() == index) {
                return pokemonMetadata;
            }
        }
        throw new PokedexException("Aucun PokemonMetadata avec cet index: " + index);
    }
}
