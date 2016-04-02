package algorithms;

import io.Action;
import io.actions.CreateAction;
import io.actions.EndOfTurnAction;
import io.actions.MoveAction;
import models.Game;
import models.Unit;
import models.UnitType;
import models.units.Peasant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        
        return actions;
    }
}
