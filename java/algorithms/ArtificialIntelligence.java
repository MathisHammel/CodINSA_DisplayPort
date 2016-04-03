package algorithms;

import rules.Action;
import models.Game;
import rules.actions.EndOfTurnAction;

import java.util.ArrayList;
import java.util.List;

public class ArtificialIntelligence {
    public static Action getNextAction(Game game) {
        return null;
    }

    public static List<Action> getNextActions(Game game) {
        List<Action> actions = new ArrayList<>();

        BehaviourInterface algorithm = new BehaviourBad();
        actions.addAll(algorithm.decideActions(game));
        
        return actions;
    }
}
