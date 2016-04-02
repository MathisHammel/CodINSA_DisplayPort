package models.units;

import models.Unit;
import models.UnitType;

public class Archer extends Unit{
    public Archer(int x, int y, int id) {
        super(UnitType.ARCHER, x, y, id);
    }

    @Override
    public Unit clone() {
        return null;
    }
}
