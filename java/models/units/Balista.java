package models.units;

import models.Unit;
import models.UnitType;

public class Balista extends Unit{
    public Balista(int x, int y, int id) {
        super(UnitType.BALISTA, x, y, id);
    }

    public Balista(int x, int y) {
        super(UnitType.BALISTA, x, y, -1);
    }

    @Override
    public Unit clone() {
        return null;
    }
}
