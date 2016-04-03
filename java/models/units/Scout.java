package models.units;

import models.Unit;
import rules.UnitType;

public class Scout extends Unit{
    public Scout(int x, int y) {
        this(x, y, -1);
    }

    public Scout(int x, int y, int id) {
        super(UnitType.SCOUT, x, y, id);
    }

    public Scout(int x, int y, int id, int actions, int health) {
        super(UnitType.ARCHER, x, y, id, actions, health);
    }

    @Override
    public Scout clone() {
        return new Scout(x, y, id, actions, health);
    }
}
