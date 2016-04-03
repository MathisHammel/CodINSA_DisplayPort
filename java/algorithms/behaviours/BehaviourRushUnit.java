package algorithms.behaviours;

import algorithms.FindPathByClosest;
import algorithms.FindPathInterface;
import algorithms.behaviours.BehaviourInterface;
import models.Game;
import models.Unit;
import rules.Action;
import rules.Rules;
import rules.UnitType;
import rules.actions.CreateAction;
import rules.actions.DestroyAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class BehaviourRushUnit implements BehaviourInterface {
    UnitType unitType;

    public BehaviourRushUnit(UnitType unitType){
        super();
        this.unitType = unitType;
    }

    @Override
    public List<Action> decideActions(Game game) {
        List<Action> operations = new ArrayList<>();
        // buy buy buyyyyyyyyy
        if (Rules.checkCreate(game, this.unitType)) {
            operations.add(new CreateAction(this.unitType));
            return operations;
        }

        // move all the unitType to the enemy city
        for (Map.Entry<Integer, Unit> intUnit : game.getCurrentPlayer().getUnits(this.unitType).entrySet()) {
            Unit unit = intUnit.getValue();
            UnitType unitType = unit.getUnitType();
            if (unitType == UnitType.ENGINEER) {
                // si on est sur une ville on attaque
                if (game.getWorld().getCell(unit.getX(), unit.getY()) == game.getOtherPlayer().getCity() && Rules.checkDestroy(game, unit.getId())) {
                    // C'est la WIN !
                    operations.add(new DestroyAction(unit));
                    return operations;
                }
            }
            // on essaie de bouger vers la ville
            FindPathInterface pathAlgo = new FindPathByClosest();
            operations.addAll(pathAlgo.evaluatePath(game, unit, game.getOtherPlayer().getCity()));
        }

        return operations;
    }
}
