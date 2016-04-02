package io.actions;

import io.Action;
import models.Building;
import models.Game;
import models.Unit;

/**
 * Orders a unit to destroy the building on which it is currently.
 * @param unitId The unit
 */
public class DestroyAction implements Action {
    int unitId;

    public DestroyAction(Unit unit) {
        this.unitId = unit.getId();
    }

    public DestroyAction(int unitId) {
        this.unitId = unitId;
    }

    public String serialize() {
        return "D,"+this.unitId;
    }

    @Override
    public boolean check(Game game) {
        Unit unit = game.getCurrentPlayer().getUnit(this.unitId);
        Building building = game.getWorld().getCell(unit.getX(), unit.getY()).getBuilding();
        return false;
    }
}
