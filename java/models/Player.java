package models;

import algorithms.Utils;
import models.units.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Player {
    public int id;
    public int gold;
    public Cell city;
    // id, unit
    public Map<Integer, Unit> units;
    public int cellsNumber;

    public final static int START_GOLD = 100;

    public Player(int id){
        this.id = id;
        this.gold = START_GOLD;
        this.units = new HashMap<>();
        this.cellsNumber = 1;
        this.city = null;
    }

    public void gainGold(World world) {
        cellsNumber = 1;
        for (int i = 0; i < world.getSize(); i++) {
            for (int j = 0; j < world.getSize(); j++) {
                if (world.getCell(i, j).owner == id) {
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
        world.getCell(x, y).unit = units.get(idUnit);
        if (world.getCell(x, y).owner != id) {
            world.getCell(x, y).owner = id;
        }
    }


    public void createUnit(char newUnitType) {
        UnitType type = UnitType.PEASANT;
        Unit u = new Peasant(city.x, city.y, id);
        switch (newUnitType) {
            case 'P':
                type = UnitType.PEASANT;
                u = new Peasant(city.x, city.y, id);
                break;
            case 'A':
                type = UnitType.ARCHER;
                u = new Archer(city.x, city.y, id);
                break;
            case 'N':
                type = UnitType.DWARF;
                u = new Dwarf(city.x, city.y, id);
                break;
            case 'B':
                type = UnitType.BALISTA;
                u = new Balista(city.x, city.y, id);
                break;
            case 'I':
                type = UnitType.ENGINEER;
                u = new Engineer(city.x, city.y, id);
                break;
            case 'E':
                type = UnitType.SCOUT;
                u = new Peasant(city.x, city.y, id);
                break;
            case 'C':
                type = UnitType.PALADIN;
                u = new Paladin(city.x, city.y, id);
                break;
            case 'S':
                type = UnitType.ARCHER;
                u = new Archer(city.x, city.y, id);
                break;
        }
        if (gold < type.cost)
            return;
        gold -= type.cost;
        int newUnitId = Collections.max(units.keySet()) + 1;
        units.put(newUnitId, u);
    }

    public void attack(World world, int idUnit, int x, int y) {
        if (world.getCell(x, y).unit == null)
            return;
        // check attack range
        int minRange = units.get(idUnit).getMinRange();
        int maxRange = units.get(idUnit).getMaxRange();
        if (world.getCell(x, y).land == Land.FOREST)
            maxRange = 1;
        int distance = Utils.infiniteDistance(x, y, units.get(idUnit).getX(), units.get(idUnit).getY());

        if (minRange > distance || distance < maxRange)
            return;
        boolean counterAttack;


        // check defense bonus

        // 3 rounds of attack

    }

    private boolean unitDie(int idUnit) {
        if (units.get(idUnit).getHealth() < 0) {
            units.remove(idUnit);
            return true;
        }
        return false;
    }
}
