package models.units;

import models.Unit;
import models.UnitType;

public class Balista extends Unit{

    public Balista(int x, int y) {
        this(x, y, -1);
    }

    public Balista(int x, int y, int id) {
        super(UnitType.BALISTA, x, y, id);
    }

    public Balista(int x, int y, int id, int actions, int health) {
        super(UnitType.ARCHER, x, y, id, actions, health);
    }

    @Override
    public Balista clone() {
        return new Balista(x, y, id, actions, health);
    }
}
