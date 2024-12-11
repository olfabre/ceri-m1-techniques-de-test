package fr.univavignon.pokedex.api;

/**
 * Un IPokemonMetadataProvider a pour but de fournir des PokemonMetadata pour un index de pokémon donné.
 * @author fv
 */
public interface IPokemonMetadataProvider {

    /**
     * Récupère et renvoie les métadonnées du pokémon.
     * dénoté par le index donné.
     *
     * @param index Index du pokémon pour lequel des métadonnées doivent être récupérées.
     * @return Métadonnées du pokémon.
     * @throws PokedexException Si le index donné n'est pas valide.
     */
    PokemonMetadata getPokemonMetadata(int index) throws PokedexException;

}
