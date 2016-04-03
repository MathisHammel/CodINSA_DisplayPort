package algorithms;

import algorithms.Utils;
import models.*;
import rules.Action;
import rules.Rules;
import rules.Simulator;
import rules.UnitType;
import rules.actions.BuildAction;
import rules.actions.CreateAction;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Creators {
    enum Size {
        SMALL,
        MEDIUM,
        LARGE
    }

    public static List<Action> predictCreates(Game game, boolean offensive, Size size) {
        double seed = Math.random();
        List<Action> actions = new LinkedList<>();

        int units = game.getCurrentPlayer().getUnits().size();
        int engineers = game.getCurrentPlayer().getUnits(UnitType.ENGINEER).size();

        UnitType unitType;

        if (engineers * 6 < units) {
            unitType = UnitType.ENGINEER;
        } else if (Utils.random(seed, game.getCurrentPlayer().getCity(), 0.4)) {
            unitType = UnitType.DWARF;
        } else if (Utils.random(seed, game.getCurrentPlayer().getCity(), 0.8)) {
            unitType = size == Size.SMALL ? UnitType.ARCHER : UnitType.BALISTA;
        } else {
            unitType = Math.random() < 0.5 ? UnitType.SOLDIER : UnitType.PALADIN;
        }

        if (Rules.checkCreate(game, unitType)) {
            actions.add(new CreateAction(unitType));
        }

        return actions;
    }
}
