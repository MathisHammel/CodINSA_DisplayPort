package algorithms;

import rules.Action;
import models.Game;
import models.Unit;
import rules.UnitType;
import rules.Rules;
import rules.actions.CreateAction;
import rules.actions.DestroyAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BehaviourBad implements BehaviourInterface {
    @Override
    public List<Action> decideActions(Game game) {
        List<Action> operations = new ArrayList<>();
        // bad strategy
        // Buy engineers, and send them to the enemy city
        if(game.getCurrentPlayer().getUnits().isEmpty()){
            // on essaie de créer un ingénieur
            if(Rules.checkCreate(game, UnitType.ENGINEER)){
                operations.add(new CreateAction(UnitType.ENGINEER));
                return operations;
            }
        }
        // move all the engineers to the enemy city
        for (Map.Entry<Integer,Unit> intUnit: game.getCurrentPlayer().getUnits().entrySet()) {
            Unit unit = intUnit.getValue();
            UnitType unitType = unit.getUnitType();
            if(unitType == UnitType.ENGINEER ){
                // si on est sur une ville on attaque
                if(game.getWorld().getCell(unit.getX(),unit.getY()) == game.getOtherPlayer().getCity() && Rules.checkDestroy(game, unit.getId())){
                    // C'est la WIN !
                    operations.add(new DestroyAction(unit));
                    return operations;
                }
                // on essaie de bouger l'ingénieur vers la ville
                FindPathInterface pathAlgo = new FindPathByClosest();
                operations.addAll(pathAlgo.evaluatePath(game, unit, game.getOtherPlayer().getCity()));
            }
        }

        return operations;
    }
}
