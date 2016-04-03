package models.units;

import models.Unit;
import rules.UnitType;

public class Peasant extends Unit{
    public Peasant(int x, int y) {
        this(x, y, -1);
    }

    public Peasant(int x, int y, int id) {
        super(UnitType.PEASANT, x, y, id);
    }

    public Peasant(int x, int y, int id, int actions, int health) {
        super(UnitType.ARCHER, x, y, id, actions, health);
    }

    @Override
    public Peasant clone() {
        return new Peasant(x, y, id, actions, health);
    }
}
