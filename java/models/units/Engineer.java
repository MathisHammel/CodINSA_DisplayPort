package models.units;

import models.Unit;
import rules.UnitType;

public class Engineer extends Unit{

    public Engineer(int x, int y) {
        this(x, y, -1);
    }

    public Engineer(int x, int y, int id) {
        super(UnitType.ENGINEER, x, y, id);
    }

    public Engineer(int x, int y, int id, int actions, int health) {
        super(UnitType.ARCHER, x, y, id, actions, health);
    }
    @Override
    public Engineer clone() {
        return new Engineer(x, y, id, actions, health);
    }
}
