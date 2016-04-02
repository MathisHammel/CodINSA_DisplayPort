package models.units;

import models.Unit;
import models.UnitType;

public class Scout extends Unit{
    public Scout(int x, int y) {
        this(x, y, -1);
    }

    public Scout(int x, int y, int id) {
        super(UnitType.SCOUT, x, y, id);
    }

    @Override
    public Unit clone() {
        return null;
    }
}
