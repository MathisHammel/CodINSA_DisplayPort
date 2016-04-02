package algorithms;

import models.Game;
import models.Unit;
import models.UnitType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BehaviourBad implements BehaviourInterface {
    @Override
    public List<String> decideActions(Game game) {
        List<String> operations = new ArrayList<>();
        // bad strategy
        // Buy engineers, and send them to the enemy city
        if(game.getCurrentPlayer().getUnits().isEmpty()){
            // on essaie de créer un ingénieur

        }
        for (Map.Entry<Integer,Unit> intUnit: game.getCurrentPlayer().getUnits().entrySet()) {
            Unit unit = intUnit.getValue();
            UnitType unitType = unit.getUnitType();
            if(unitType == UnitType.ENGINEER ){
                // on essaie de bouger l'ingénieur vers la ville
                FindPathInterface pathAlgo = new FindPathByClosest();
                operations.addAll(pathAlgo.evaluatePath(game, unit, game.getOtherPlayer().getCity()));
            }
        }

        return operations;
    }
}
