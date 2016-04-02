package models.units;

import models.Unit;
import models.UnitType;

public class Engineer extends Unit{

    public Engineer(int x, int y) {
        this(x, y, -1);
    }

    public Engineer(int x, int y, int id) {
        super(UnitType.ENGINEER, x, y, id);
    }

    @Override
    public Unit clone() {
        return null;
    }
}
