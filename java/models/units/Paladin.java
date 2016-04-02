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

    public Paladin(int x, int y, int id, int actions, int health) {
        super(UnitType.ARCHER, x, y, id, actions, health);
    }

    @Override
    public Paladin clone() {
        return new Paladin(x, y, id, actions, health);
    }
}
