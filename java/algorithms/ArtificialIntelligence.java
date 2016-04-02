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

public class ArtificialIntelligence {
    public static Action getNextAction(Game game) {
        return null;
    }

    public static List<Action> getNextActions(Game game) {
        List<Action> actions = new ArrayList<Action>();
        Unit peasant = new Peasant(2, 1);
        actions.add(new CreateAction(peasant.getUnitType()));
        actions.add(new MoveAction(peasant, 2, 2));
        actions.add(new EndOfTurnAction());
        return actions;
    }
}
