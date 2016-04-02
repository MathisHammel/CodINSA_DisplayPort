package models.units;

import models.Unit;
import models.UnitType;

public class Paladin extends Unit{
    public Paladin(int x, int y, int id) {
        super(UnitType.PALADIN, x, y, id);
    }

    public Paladin(int x, int y) {
        super(UnitType.PALADIN, x, y, -1);
    }

    @Override
    public Unit clone() {
        return null;
    }
}
