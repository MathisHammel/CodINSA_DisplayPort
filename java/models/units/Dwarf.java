package models.units;

import models.Unit;
import models.UnitType;

public class Dwarf extends Unit{

    public Dwarf(int x, int y) {
        this(x, y, -1);
    }

    public Dwarf(int x, int y, int id) {
        super(UnitType.DWARF, x, y, id);
    }

    public Dwarf(int x, int y, int id, int actions, int health) {
        super(UnitType.ARCHER, x, y, id, actions, health);
    }

    @Override
    public Dwarf clone() {
        return new Dwarf(x, y, id, actions, health);
    }
}
