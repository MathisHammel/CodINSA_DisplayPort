package algorithms.creators;

import algorithms.Utils;
import models.Game;
import models.World;
import rules.Action;
import rules.Rules;
import rules.UnitType;
import rules.actions.CreateAction;

public class CreatorLargeOffensive implements CreatorsInterface {
    @Override
    public Action create(Game game) {
        double seed = Math.random();
        Action action = null;

        int units = game.getCurrentPlayer().getUnits().size();
        int engineers = game.getCurrentPlayer().getUnits(UnitType.ENGINEER).size();

        UnitType unitType;

        if (engineers * 6 < units) {
            unitType = UnitType.ENGINEER;
        } else if (Utils.random(seed, game.getCurrentPlayer().getCity(), 0.4)) {
            unitType = UnitType.DWARF;
        } /*else if (Utils.random(seed, game.getCurrentPlayer().getCity(), 0.8)) {
            unitType = UnitType.BALISTA;
        } else*/ {
            unitType = (Math.random() < 0.5) ? UnitType.SOLDIER : UnitType.PALADIN;
        }

        if (Rules.checkCreate(game, unitType)) {
            action = new CreateAction(unitType);
        }

        return action;
    }
}
