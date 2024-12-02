package fr.univavignon.pokedex.api;

/**
 * Pokemon metadata POJO.
 *
 * @author fv
 */
public class PokemonMetadata {

    /** Pokemon index. **/
    private final int index;

    /** Pokemon name. **/
    private final String name;

    /** Pokemon attack level. **/
    private final int attack;

    /** Pokemon defense level. **/
    private final int defense;

    /** Pokemon stamina level. **/
    private final int stamina;

    /**
     * Default constructor.
     *
     * @param index Pokemon index.
     * @param name Pokemon name.
     * @param attack Attack level.
     * @param defense Defense level.
     * @param stamina Stamina level.
     */
    public PokemonMetadata(final int index, final String name, final int attack, final int defense, final int stamina) {
        this.index = index;
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.stamina = stamina;
    }

    /**
     * Returns the Pokémon index.
     *
     * @return the Pokémon index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Returns the Pokémon name.
     *
     * @return the Pokémon name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the Pokémon attack level.
     *
     * @return the Pokémon attack level
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Returns the Pokémon defense level.
     *
     * @return the Pokémon defense level
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Returns the Pokémon stamina level.
     *
     * @return the Pokémon stamina level
     */
    public int getStamina() {
        return stamina;
    }

}