package models.units;

import models.Unit;
import models.UnitType;

public class Scout extends Unit{
    public Scout(int x, int y, int id) {
        super(UnitType.SCOUT, x, y, id);
    }

    public Scout(int x, int y) {
        super(UnitType.SCOUT, x, y, -1);
    }

    @Override
    public Unit clone() {
        return null;
    }
}
