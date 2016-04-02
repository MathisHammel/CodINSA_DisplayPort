package models;

/**
 * Created by Charles on 02/04/2016.
 */
public class UnitType {
    protected int maxActions;
    protected int maxHealth;
    protected int strength;
    protected int defense;
    protected int minRange;
    protected int maxRange;
    protected int cost;

    private UnitType (
            int maxActions,
            int strength,
            int defense,
            int maxHealth,
            int minRange,
            int maxRange,
            int cost
    ) {
        this.maxActions = maxActions;
        this.maxHealth = maxHealth;
        this.strength = strength;
        this.defense = defense;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.cost = cost;
    }

    public final static UnitType PEASANT = new UnitType(2, 1, 1, 1, 1, 1, 10);
    public final static UnitType SCOUT = new UnitType(6, 3, 1, 2, 1, 1, 30);
    public final static UnitType ARCHER = new UnitType(4, 3, 2, 2, 2, 3, 50);
    public final static UnitType SOLDIER = new UnitType(4, 3, 3, 3, 1, 1, 50);
    public final static UnitType DWARF = new UnitType(4, 4, 4, 4, 1, 1, 100);
    public final static UnitType PALADIN = new UnitType(6, 4, 4, 3, 1, 1, 100);
    public final static UnitType BALISTA = new UnitType(2, 6, 4, 4, 3, 5, 100);
    public final static UnitType ENGINEER = new UnitType(4, 0, 1, 2, 0, 0, 50);
}
