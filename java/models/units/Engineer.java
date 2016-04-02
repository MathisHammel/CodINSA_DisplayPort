package models.units;

import models.Unit;
import models.UnitType;

public class Engineer extends Unit{
    public Engineer(int x, int y, int id) {
        super(UnitType.ENGINEER, x, y, id);
    }

    public Engineer(int x, int y) {
        super(UnitType.ENGINEER, x, y, -1);
    }

    @Override
    public Unit clone() {
        return null;
    }
}
