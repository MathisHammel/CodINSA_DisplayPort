package models;

import algorithms.Utils;
import models.units.*;
import rules.UnitType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Player extends GameEntity {
    public final static int START_GOLD = 100;

    protected int id;
    protected int gold;
    protected int cityX;
    protected int cityY;

    // id, unit
    protected Map<Integer, Unit> units;
    protected int cellsNumber;

    public Player(int id){
        this(id, START_GOLD, new HashMap<Integer, Unit>());
    }
    
    public Player(int id, int gold, Map<Integer, Unit> units){
        this(id, gold, units, -1, -1, 1);
    }

    public Player(int id, int gold, Map<Integer, Unit> units, int cityX, int cityY, int cellsNumber) {
        super(null);
        this.id = id;
        this.gold = gold;
        this.units = units;
        this.cellsNumber = cellsNumber;
        this.cityX = cityX;
        this.cityY = cityY;
    }

    public Player clone() {
        Map<Integer, Unit> units = new HashMap<Integer, Unit>();
        for(int id: units.keySet()) {
            units.put(id, units.get(id).clone());
        }
        return new Player(id, gold, units, cityX, cityY, cellsNumber);
    }

    public void bindGame(Game g) {
        this.setGame(g);
        for(int id: units.keySet()) {
            units.get(id).bindGame(g);
        }
    }

    public int getId() {
        return this.id;
    }

    public int getGold() {
        return this.gold;
    }

    public int getCellsNumber() {
        return this.cellsNumber;
    }

    public Cell getCity() {
        return this.getGame().getWorld().getCell(this.cityX, this.cityY);
    }

    public Map<Integer, Unit> getUnits() {
        return this.units;
    }
    
    public Map<Integer, Unit> getUnits(UnitType type) {
        Map<Integer, Unit> ret = new HashMap<>();
        
        for(Map.Entry<Integer, Unit> unit : units.entrySet()) {
            if(unit.getValue().unitType == type)
                ret.put(unit.getKey(), unit.getValue());
        }
        
        return ret;
    }

    public Unit getUnit(int unitId) {
        return this.units.get(unitId);
    }

    public Unit setUnit(int unitId, Unit unit) {
        if(this.game != null) {
            unit.bindGame(this.getGame());
        }
        return this.units.put(unitId, unit);
    }

    public Player setCity(int x, int y) {
        this.cityX = x;
        this.cityY = y;
        return this;
    }

    public Player setCity(Cell city) {
        this.cityX = city.getX();
        this.cityY = city.getY();
        return this;
    }

    private boolean unitDie(int idUnit) {
        if (units.get(idUnit).getHealth() < 0) {
            units.remove(idUnit);
            return true;
        }
        return false;
    }

    public void gainGold(World world) {
        cellsNumber = 1;
        for (int i = 0; i < world.getSize(); i++) {
            for (int j = 0; j < world.getSize(); j++) {
                if (world.getCell(i, j).getOwner().id == id) {
                    cellsNumber += 1;
                }
            }
        }
        gold += 5 * cellsNumber;
    }

    public void moveUnit(World world, int idUnit, int x, int y, int newAction) {
        units.get(idUnit).setX(x);
        units.get(idUnit).setY(y);
        units.get(idUnit).setActions(newAction);
        world.getCell(x, y).setUnit(idUnit);
        if (world.getCell(x, y).getOwner().id != id) {
            world.getCell(x, y).setOwner(id);
        }
    }

    public void attack(World world, Player otherPlayer, int idUnit, int x, int y) {

        // check defense bonus
        int defense = units.get(idUnit).getDefense();
        if (world.getCell(units.get(idUnit).getX(), units.get(idUnit).getY()).getBuilding() == Building.FORT)
            defense += 2;
        int defenseOther = world.getCell(x, y).getUnit().getDefense();
        if (world.getCell(x, y).getBuilding() == Building.FORT)
            defenseOther += 2;

        // 3 rounds of attack
        int ran;
        for (int i = 0; i < 3; i++) {
            ran = 1 + (int)(Math.random()) * 6;
            if (ran == 6) {
                world.getCell(x, y).getUnit().setHealth(world.getCell(x, y).getUnit().getHealth() - units.get(idUnit).getStrength());
            } else if (ran >= defenseOther + 3) {
                world.getCell(x, y).getUnit().setHealth(world.getCell(x, y).getUnit().getHealth() - units.get(idUnit).getStrength());
            }
            if (otherPlayer.unitDie(world.getCell(x, y).getUnit().getId()))
                break;
            if (false) {
                ran = 1 + (int)(Math.random()) * 6;
                if (ran == 6) {
                   units.get(idUnit).setHealth(units.get(idUnit).getHealth() - world.getCell(x, y).getUnit().getStrength());
                } else if (ran >= defense + 3) {
                    units.get(idUnit).setHealth(units.get(idUnit).getHealth() - world.getCell(x, y).getUnit().getStrength());
                }
                if (unitDie(idUnit))
                    break;
            }
        }
    }
}
