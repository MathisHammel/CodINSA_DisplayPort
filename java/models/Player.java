package models;

import models.units.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Player {
    int id;
    int gold;
    Cell city;
    // id, unit
    Map<Integer, Unit> units;
    int cellsNumber;

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
}
