package models;

import algorithms.Utils;

import java.util.HashSet;
import java.util.Set;

public abstract class Unit extends GameEntity {
    private int actions;
    private int health;
    private UnitType unitType;
    private int x;
    private int y;
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public Unit(UnitType type, int x, int y, int id) {
        super(null);
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

    public abstract Unit clone();


    public Set<Cell> getReachableCells(World world){
        Set<Cell> reachableCells = this.getNeighboursCells(world);
        for (Cell c : reachableCells) {
            // on sait qu'il est possible
            int action = Utils.checkMove(c, this.getX(), this.getY(), this.getUnitType(), this.getActions());
            // on sauvegarde la position et energie de l'unit pour la restituer ensuite.
            int oldAction = this.getActions();
            int oldX = this.getX();
            int oldY = this.getY();
            // on simule un deplacement
            this.setActions(action);
            this.setX(c.getX());
            this.setY(c.getY());

            reachableCells.addAll(this.getReachableCells(world));

            // on restitue l'ancien etat
            this.setActions(oldAction);
            this.setX(oldX);
            this.setY(oldY);
        }
        return reachableCells;
    }

    public void move(World world, int x, int y, int newAction) {
        this.setX(x);
        this.setY(y);
        this.setActions(newAction);
        world.getCell(x, y).setUnit(this.id);
        if (world.getCell(x, y).getOwner().id != id) {
            world.getCell(x, y).setOwner(id);
        }
    }

    // OK
    private Set<Cell> getNeighboursCells(World world){
        Set<Cell> reachableCells = new HashSet<>();
        for (int x = this.getX()-1; x <= this.getX()+1; x++) {
            for(int y = this.getY()-1; y <= this.getY()+1; y++){
                if(x != this.getX() && y != this.getY()){
                    Cell c = world.getCell(x,y);
                    int res = Utils.checkMove(c, this.getX(), this.getY(), this.getUnitType(), this.getActions());
                    //int res = this.checkMove(c);
                    if ( res >= 0){
                        // c'est une position atteignable
                        reachableCells.add(c);
                    }
                }
            }
        }
        return reachableCells;
    }
}
