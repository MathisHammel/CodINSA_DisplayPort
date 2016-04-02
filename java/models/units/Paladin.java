package models.units;

import models.Unit;
import models.UnitType;

public class Paladin extends Unit{
    public Paladin(int x, int y) {
        this(x, y, -1);
    }

    public Paladin(int x, int y, int id) {
        super(UnitType.PALADIN, x, y, id);
    }



    @Override
    public Unit clone() {
        return null;
    }
}
