package models.units;

import models.Unit;
import models.UnitType;

public class Dwarf extends Unit{
    public Dwarf(int x, int y, int id) {
        super(UnitType.DWARF, x, y, id);
    }

    @Override
    public Unit clone() {
        return null;
    }
}
