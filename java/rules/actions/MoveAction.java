package rules.actions;

import algorithms.Utils;
import rules.Action;
import models.Cell;
import models.Game;
import models.Unit;
import rules.Rules;

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

    public MoveAction(Unit unit, int x, int y) {
        this.unitId = unit.getId();
        this.x = x;
        this.y = y;
    }

    public MoveAction(int unitId, int x, int y) {
        this.unitId = unitId;
        this.x = x;
        this.y = y;
    }

    public String serialize() {
        return "M,"+this.unitId +","+this.x+","+this.y;
    }

    @Override
    public boolean check(Game game) {
        return Rules.checkMove(game, this.unitId, this.x, this.y);
    }
}
