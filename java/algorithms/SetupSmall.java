package algorithms;

import java.util.List;
import models.Game;
import rules.Action;
import rules.Rules;
import rules.UnitType;
import rules.actions.CreateAction;

public class SetupSmall implements SetupInterface {

    @Override
    public Action deploy(Game game) {        
        //2 scouts, 1 soldat, 1 archer
        if(game.getCurrentPlayer().getUnits(UnitType.SCOUT).size() < 2) {
            if(Rules.checkCreate(game, UnitType.SCOUT))
                return new CreateAction(UnitType.SCOUT);
        } else if(game.getCurrentPlayer().getUnits(UnitType.SOLDIER).size() < 1) {
            if(Rules.checkCreate(game, UnitType.SOLDIER))
                return new CreateAction(UnitType.SOLDIER);
        } else if(game.getCurrentPlayer().getUnits(UnitType.ARCHER).size() < 1) {
            if(Rules.checkCreate(game, UnitType.ARCHER))
                return new CreateAction(UnitType.ARCHER);
        }
        
        return null;
    }
    
}
