package models;

import algorithms.Utils;
import models.units.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Player extends GameEntity {
    public final static int START_GOLD = 100;

    protected int id;
    protected int gold;
    protected Cell city;
    // id, unit
    protected Map<Integer, Unit> units;
    protected int cellsNumber;

    public Player(int id){
        this(id, START_GOLD, new HashMap<Integer, Unit>());
    }
    
    public Player(int id, int gold, Map<Integer, Unit> units){
        this(id, gold, units, null, 1);
    }

    public Player(int id, int gold, Map<Integer, Unit> units, Cell city, int cellsNumber) {
        super(null);
        this.id = id;
        this.gold = gold;
        this.units = units;
        this.cellsNumber = cellsNumber;
        this.city = city;
    }

    public Player clone() {
        Map<Integer, Unit> units = new HashMap<Integer, Unit>();
        for(int id: units.keySet()) {
            units.put(id, units.get(id).clone());
        }
        return new Player(id, gold, units, city, cellsNumber);
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
        return null;
    }

    public Map<Integer, Unit> getUnits() {
        return this.units;
    }

    public Unit getUnit(int unitId) {
        return this.units.get(unitId);
    }

    public Unit setUnit(int unitId, Unit unit) {
        return this.units.put(unitId, unit);
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


    public void createUnit(char newUnitType) {
        UnitType type = UnitType.PEASANT;
        Unit u = new Peasant(city.getX(), city.getY(), id);
        switch (newUnitType) {
            case 'P':
                type = UnitType.PEASANT;
                u = new Peasant(city.getX(), city.getY(), id);
                break;
            case 'A':
                type = UnitType.ARCHER;
                u = new Archer(city.getX(), city.getY(), id);
                break;
            case 'N':
                type = UnitType.DWARF;
                u = new Dwarf(city.getX(), city.getY(), id);
                break;
            case 'B':
                type = UnitType.BALISTA;
                u = new Balista(city.getX(), city.getY(), id);
                break;
            case 'I':
                type = UnitType.ENGINEER;
                u = new Engineer(city.getX(), city.getY(), id);
                break;
            case 'E':
                type = UnitType.SCOUT;
                u = new Peasant(city.getX(), city.getY(), id);
                break;
            case 'C':
                type = UnitType.PALADIN;
                u = new Paladin(city.getX(), city.getY(), id);
                break;
            case 'S':
                type = UnitType.ARCHER;
                u = new Archer(city.getX(), city.getY(), id);
                break;
        }
        if (gold < type.cost) {
            return;
        }
        gold -= type.cost;
        int newUnitId = Collections.max(units.keySet()) + 1;
        u.setActions(2);
        units.put(newUnitId, u);
    }

    public void attack(World world, Player otherPlayer, int idUnit, int x, int y) {
        if (world.getCell(x, y).getUnit() == null){
            return;
        }
        // check attack range
        int minRange = units.get(idUnit).getMinRange();
        int maxRange = units.get(idUnit).getMaxRange();
        if (world.getCell(x, y).getLand() == Land.FOREST){
            maxRange = 1;
        }

        int minRangeOther = world.getCell(x, y).getUnit().getMinRange();
        int maxRangeOther = world.getCell(x, y).getUnit().getMaxRange();
        if (world.getCell(units.get(idUnit).getX(), units.get(idUnit).getY()).getLand() == Land.FOREST)
            maxRangeOther = 1;

        int distance = Utils.infiniteDistance(x, y, units.get(idUnit).getX(), units.get(idUnit).getY());
        if (minRange > distance || distance < maxRange)
            return;
        boolean counterAttack = true;
        if (minRangeOther > distance || distance < maxRangeOther)
            counterAttack = false;

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
            if (counterAttack) {
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
