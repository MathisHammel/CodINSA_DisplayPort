package algorithms;

import rules.Action;
import models.Game;
import rules.UnitType;
import rules.actions.EndOfTurnAction;

import java.util.ArrayList;
import java.util.List;

public class ArtificialIntelligence {
    public static Action getNextAction(Game game) {
        return null;
    }

    public static List<Action> getNextActions(Game game) {
        game.getWorld().calculateScores();
        List<Action> actions = new ArrayList<>();

        BehaviourInterface algorithm = new BehaviourRushUnit(UnitType.BALISTA);
        actions.addAll(algorithm.decideActions(game));


        return actions;
    }
}
