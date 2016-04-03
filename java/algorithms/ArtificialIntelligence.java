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
        /*
        Unit peasant = null;
        for(Map.Entry<Integer, Unit> unit : game.getOurPlayer().getUnits().entrySet()) {
            if(unit.getValue().getUnitType() == UnitType.PEASANT) {
                peasant = unit.getValue();
            }
        }
        
        List<Action> actions = new ArrayList<>();
        
        if(peasant == null) {
            peasant = new Peasant(2, 1);
            actions.add(new CreateAction(peasant.getUnitType()));
        } else {
            int i = peasant.getX()+1;
            if(i == game.getWorld().getSize()) i -= 2;
            actions.add(new MoveAction(peasant, i, 1));
            actions.add(new EndOfTurnAction());
        }
        */
        
        List<Action> actions = new ArrayList<>();

        BehaviourInterface algorithm = new BehaviourBad();
        actions.addAll(algorithm.decideActions(game));
        
        if(actions.isEmpty()) actions.add(new EndOfTurnAction());
        
        return actions;
    }
}
