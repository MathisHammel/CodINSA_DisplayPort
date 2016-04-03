package models;

import algorithms.Utils;
import rules.Rules;
import rules.UnitType;

import java.util.*;

public abstract class Unit extends GameEntity {
    protected int actions;
    protected int health;
    protected UnitType unitType;
    protected int x;
    protected int y;
    protected int id;

    public Unit(UnitType type, int x, int y, int id) {
        this(type, x, y, id, type.maxActions, type.maxHealth);
    }

    public Unit(UnitType type, int x, int y, int id, int actions, int health) {
        super(null);
        this.unitType = type;
        this.actions = actions;
        this.health = health;
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public void bindGame(Game g) {
        this.setGame(g);
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

    public void setId(int id) {
        this.id = id;
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

    public List<Cell> attackable() {
        List<Cell> cells = new ArrayList<>();
        int rMin = this.getMinRange();
        int rMax = this.getMaxRange();
        int curX = this.getX();
        int curY = this.getY();

        for (int r = rMin; r <= rMax; r++) {
            for(int i = 0; i < 2*r; i++) {
                Cell c1 = this.getGame().getWorld().getCell(curX - r + i, curY - r);
                Cell c2 = this.getGame().getWorld().getCell(curX + r, curY - r + i);
                Cell c3 = this.getGame().getWorld().getCell(curX + r - i, curY + r);
                Cell c4 = this.getGame().getWorld().getCell(curX - r, curY + r - i);
                if(c1 != null && Rules.checkAttack(this.getGame(), this.getId(), c1.getX(), c1.getY())) {
                    cells.add(c1);
                }
                if(c2 != null && Rules.checkAttack(this.getGame(), this.getId(), c2.getX(), c2.getY())) {
                    cells.add(c2);
                }
                if(c3 != null && Rules.checkAttack(this.getGame(), this.getId(), c3.getX(), c3.getY())) {
                    cells.add(c3);
                }
                if(c4 != null && Rules.checkAttack(this.getGame(), this.getId(), c4.getX(), c4.getY())) {
                    cells.add(c4);
                }
            }
        }
        return cells;
    }


    public abstract Unit clone();

    public Map<Cell, ReachableResult> getReachableCells(World world){
        Map<Cell, ReachableResult> reachableCells = this.getNeighboursCells(world);
        for (Cell c : reachableCells.keySet()) {
            // on sauvegarde la position et energie de l'unit pour la restituer ensuite.
            int oldAction = this.getActions();
            int oldX = this.getX();
            int oldY = this.getY();
            // on simule un deplacement
            // on sait qu'il est possible
            int action = this.getActions() - reachableCells.get(c).cost;
            this.setActions(action);
            this.setX(c.getX());
            this.setY(c.getY());

            // appel récursif + FUSIIOOOOON
            Map<Cell, ReachableResult> newResults = this.getReachableCells(world);
            for (Map.Entry<Cell, ReachableResult> result : newResults.entrySet()) {
                if(reachableCells.containsKey(result.getKey()) &&
                        result.getValue().cost < reachableCells.get(result.getKey()).cost){
                    reachableCells.put(result.getKey(), result.getValue());
                }
            }

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
        world.getCell(x, y).setUnit(this.getId());
        if (world.getCell(x, y).getOwner().getId() != this.getId()) {
            world.getCell(x, y).setOwner(this.getId());
        }
    }

    // OK
    public Map<Cell, ReachableResult> getNeighboursCells(World world){
        Map<Cell, ReachableResult> reachableCells = new HashMap<>();
        for (int x = this.getX()-1; x <= this.getX()+1; x++) {
            for(int y = this.getY()-1; y <= this.getY()+1; y++){
                if(!(x == this.getX() && y == this.getY())){
                    Cell c = world.getCell(x,y);
                    int res = Utils.getActionsAfterMove(c, this.getX(), this.getY(), this.getUnitType(), this.getActions());
                    //int res = this.getActionsAfterMove(c);
                    if ( res >= 0){
                        // c'est une position atteignable
                        reachableCells.put(c,new ReachableResult(world.getCell(this.getX(), this.getY()), this.getActions() - res));
                    }
                }
            }
        }
        return reachableCells;
    }

    //pour dijkstra
    public class ReachableResult{
        public Cell from;
        public int cost;

        public ReachableResult(Cell from, int cost){
            this.from = from;
            this.cost = cost;
        }
    }

}
