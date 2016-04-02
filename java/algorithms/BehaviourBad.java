package algorithms;

import models.Game;
import models.Unit;
import models.UnitType;

import java.util.List;
import java.util.Map;

public class BehaviourBad implements BehaviourInterface {
    @Override
    public List<String> decideActions(Game game) {
        // bad strategy
        // Buy engineers, and send them to the enemy city
        for (Map.Entry<Integer,Unit> intUnit: game.currentPlayer.units.entrySet()) {
            Unit unit = intUnit.getValue();
            UnitType unitType = unit.getUnitType();
            if(unitType == UnitType.ENGINEER){

            }
        }
        return null;
    }
}
