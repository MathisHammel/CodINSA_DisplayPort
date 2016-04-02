package models.units;

import models.Unit;
import models.UnitType;

public class Archer extends Unit{
    public Archer(int x, int y, int id) {
        super(UnitType.ARCHER, x, y, id);
    }

    public Archer(int x, int y) {
        super(UnitType.ARCHER, x, y, -1);
    }

    @Override
    public Unit clone() {
        return null;
    }
}
