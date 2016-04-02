package models;

/**
 * Created by Charles on 02/04/2016.
 */
public class Unit {
    private int actions;
    private int health;
    private UnitType unitType;
    private int x;
    private int y;
    private int id;

    public Unit(UnitType type, int x, int y, int id) {
        this.unitType = type;
        this.actions = type.maxActions;
        this.health = type.maxHealth;
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public int getActions(){
        return this.actions;
    }

    public int getHealth(){
        return this.health;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getId(){
        return this.id;
    }

    public Unit setActions(int actions){
        this.actions = actions;
        return this;
    }

    public Unit setHealth(int health){
        this.health = health;
        return this;
    }

    public Unit setX(int x){
        this.x = x;
        return this;
    }

    public Unit setY(int y){
        this.y = y;
        return this;
    }

    public UnitType getUnitType(){
        return this.unitType;
    }

    public int getMaxActions(){
        return this.unitType.maxActions;
    }

    public int getMaxHealth(){
        return this.unitType.maxHealth;
    }

    public int getMinRange(){
        return this.unitType.minRange;
    }

    public int getMaxRange(){
        return this.unitType.maxRange;
    }

    public int getStrength(){
        return this.unitType.strength;
    }

    public int getDefense(){
        return this.unitType.defense;
    }

    public int getCost(){
        return this.unitType.cost;
    }

    /*public void moveTo(int x, int y){

    }*/

}
