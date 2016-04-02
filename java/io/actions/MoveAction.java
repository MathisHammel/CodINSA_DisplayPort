package io.actions;

import io.Action;
import models.Unit;

/**
 * Attaque un autre personnage.
 * unitId Unité qui doit se déplacer
 * x Coordonnée x à atteindre
 * y Coordonnée y à atteindre
 */
public class MoveAction implements Action {
    int unitId;
    int x;
    int y;

    MoveAction(Unit unit, int x, int y) {
        this.unitId = unit.getId();
        this.x = x;
        this.y = y;
    }

    MoveAction(int unitId, int x, int y) {
        this.unitId = unitId;
        this.x = x;
        this.y = y;
    }

    public String serialize() {
        return "M,"+this.unitId +","+this.x+","+this.y;
    }
}
