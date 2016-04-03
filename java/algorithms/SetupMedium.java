package algorithms;

import java.util.List;
import models.Game;
import rules.Action;
import rules.Rules;
import rules.UnitType;
import rules.actions.CreateAction;

public class SetupMedium implements SetupInterface {

    @Override
    public Action deploy(Game game) {        
        //2 scouts, 1 soldat, 1 autre scout
        if(game.getCurrentPlayer().getUnits(UnitType.SCOUT).size() < 2) {
            if(Rules.checkCreate(game, UnitType.SCOUT))
                return new CreateAction(UnitType.SCOUT);
        } else if(game.getCurrentPlayer().getUnits(UnitType.SOLDIER).size() < 1) {
            if(Rules.checkCreate(game, UnitType.SOLDIER))
                return new CreateAction(UnitType.SOLDIER);
        } else if(game.getCurrentPlayer().getUnits(UnitType.SCOUT).size() < 3) {
            if(Rules.checkCreate(game, UnitType.SCOUT))
                return new CreateAction(UnitType.SCOUT);
        }
        
        return null;
    }
    
}
