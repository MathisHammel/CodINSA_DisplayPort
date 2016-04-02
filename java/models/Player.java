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
}
