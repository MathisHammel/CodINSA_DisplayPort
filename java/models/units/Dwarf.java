package models.units;

import models.Unit;
import models.UnitType;

public class Dwarf extends Unit{
    public Dwarf(int x, int y, int id) {
        super(UnitType.DWARF, x, y, id);
    }

    public Dwarf(int x, int y) {
        super(UnitType.DWARF, x, y, -1);
    }

    @Override
    public Unit clone() {
        return null;
    }
}
