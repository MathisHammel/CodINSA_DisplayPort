package io.actions;

import io.Action;
import models.Unit;

/**
 * Orders a unit to destroy the building on which it is currently.
 * @param unit The unit
 */
public class DestroyAction implements Action {
    int unitId;

    DestroyAction(Unit unit) {
        this.unitId = unit.getId();
    }

    DestroyAction(int unitId) {
        this.unitId = unitId;
    }

    public String serialize() {
        return "D,"+this.unitId;
    }
}
