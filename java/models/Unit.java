package models;

/**
 * Created by Charles on 02/04/2016.
 */
public class Unit {
    public int actions;
    public int health;
    public UnitType unitType;
    public int x;
    public int y;

    public Unit(UnitType type, int x, int y) {
        this.unitType = type;
        this.actions = type.maxActions;
        this.health = type.maxHealth;
        this.x = x;
        this.y = y;
    }
}
