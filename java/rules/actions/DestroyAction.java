package rules.actions;

import rules.Action;
import models.Building;
import models.Game;
import models.Unit;
import rules.Rules;

/**
 * Orders a unit to destroy the building on which it is currently.
 */
public class DestroyAction implements Action {
    int unitId;

    public DestroyAction(Unit unit) {
        this.unitId = unit.getId();
    }

    public DestroyAction(int engineerId) {
        this.unitId = engineerId;
    }

    public String serialize() {
        return "D,"+this.unitId;
    }

    @Override
    public boolean check(Game game) {
        return Rules.checkDestroy(game, this.unitId);
    }
}
