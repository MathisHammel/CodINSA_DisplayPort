package models.units;

import models.Unit;
import models.UnitType;

public class Soldier extends Unit{
    public Soldier(int x, int y) {
        this(x, y, -1);
    }

    public Soldier(int x, int y, int id) {
        super(UnitType.SOLDIER, x, y, id);
    }

    public Soldier(int x, int y, int id, int actions, int health) {
        super(UnitType.ARCHER, x, y, id, actions, health);
    }

    @Override
    public Soldier clone() {
        return new Soldier(x, y, id, actions, health);
    }
}
