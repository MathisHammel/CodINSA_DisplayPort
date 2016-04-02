package models.units;

import models.Unit;
import models.UnitType;

public class Peasant extends Unit{
    public Peasant(int x, int y, int id) {
        super(UnitType.PEASANT, x, y, id);
    }

    public Peasant(int x, int y) {
        super(UnitType.PEASANT, x, y, -1);
    }

    @Override
    public Unit clone() {
        return null;
    }
}
