package models;

import java.util.HashMap;
import java.util.Map;

public class Player {
    int id;
    int gold;
    Cell city;
    // id, unit
    Map<Integer, Unit> units;
    int cells;

    public final static int START_GOLD = 100;

    public Player(int id){
        this.id = id;
        this.gold = START_GOLD;
        this.units = new HashMap<>();
        this.cells = 1;
        this.city = null;
    }

    public void gainGold(World world) {
        for (int i = 0; i < world.getSize(); i++) {
            for (int j = 0; j < world.getSize(); j++) {
                if (world.getCell(i, j).owner == id)
                    gold += 5;   // 5 gold per land owned
            }
        }
    }

    public void moveUnit(int id, int x, int y, int newAction) {
        units.get(id).setX(x);
        units.get(id).setY(y);
        units.get(id).setActions(newAction);
    }
}
