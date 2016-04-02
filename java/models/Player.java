package models;

import models.units.Engineer;

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

    }
}
