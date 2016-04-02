package models.units;

import models.Unit;
import models.UnitType;

public class Archer extends Unit{

    public Archer(int x, int y) {
        this(x, y, -1);
    }

    public Archer(int x, int y, int id) {
        super(UnitType.ARCHER, x, y, id);
    }

    public Archer(int x, int y, int id, int actions, int health) {
        super(UnitType.ARCHER, x, y, id, actions, health);
    }

    @Override
    public Archer clone() {
        return new Archer(x, y, id, actions, health);
    }
}
