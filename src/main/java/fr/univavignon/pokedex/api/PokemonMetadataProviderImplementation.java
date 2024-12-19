package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.List;

public class PokemonMetadataProviderImplementation {

    // Liste des métadonnées des Pokémon
    private List<PokemonMetadata> pokemonMetadatas;
    private static PokemonMetadataProviderImplementation instance;

    // Méthode pour obtenir l'instance unique (Singleton)
    public static PokemonMetadataProviderImplementation getInstance() {
        if (instance == null) {
            instance = new PokemonMetadataProviderImplementation();
        }
        return instance;
    }

    // Constructeur privé pour empêcher l'instanciation directe
    private PokemonMetadataProviderImplementation() {
        this.pokemonMetadatas = new ArrayList<>();
        initializePokemonMetadata();
    }

    // Initialisation des métadonnées des Pokémon
    private void initializePokemonMetadata() {
        PokemonMetadata Bulbizarre = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        PokemonMetadata Salamèche = new PokemonMetadata(3, "Salamèche", 126, 126, 90);
        PokemonMetadata Aquali = new PokemonMetadata(19, "Aquali", 126, 126, 150);

        pokemonMetadatas.add(Bulbizarre);
        pokemonMetadatas.add(Salamèche);
        pokemonMetadatas.add(Aquali);
    }

    // Méthode pour récupérer les métadonnées d'un Pokémon par son index
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        for (PokemonMetadata pokemonMetadata : pokemonMetadatas) {
            if (pokemonMetadata.getIndex() == index) {
                return pokemonMetadata;
            }
        }
        throw new PokedexException("Aucun PokemonMetadata avec cet index: " + index);
    }
}
