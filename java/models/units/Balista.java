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

    @Override
    public Unit clone() {
        return null;
    }
}
