package models.units;

import models.Unit;
import models.UnitType;

public class Soldier extends Unit{
    public Soldier(int x, int y, int id) {
        super(UnitType.SOLDIER, x, y, id);
    }

    public Soldier(int x, int y) {
        super(UnitType.SOLDIER, x, y, -1);
    }

    @Override
    public Unit clone() {
        return null;
    }
}
