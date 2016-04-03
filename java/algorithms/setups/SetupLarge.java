package algorithms.setups;

import models.Game;
import rules.Action;
import rules.Rules;
import rules.UnitType;
import rules.actions.CreateAction;

public class SetupLarge implements SetupInterface {

    @Override
    public Action deploy(Game game) {        
        // 2 scouts
        // 1 soldat
        // 1 scout
        // 1 baliste
        // 1 ingé
        // 1 nain
        // 2 ingés
        // 3 ingés, 6 scouts
        if(game.getCurrentPlayer().getUnits(UnitType.SCOUT).size() < 2) {
            if(Rules.checkCreate(game, UnitType.SCOUT)) {
                return new CreateAction(UnitType.SCOUT);
            }
        } else if(game.getCurrentPlayer().getUnits(UnitType.SOLDIER).size() < 1) {
            if(Rules.checkCreate(game, UnitType.SOLDIER)) {
                return new CreateAction(UnitType.SOLDIER);
            }
        } else if(game.getCurrentPlayer().getUnits(UnitType.SCOUT).size() < 3) {
            if(Rules.checkCreate(game, UnitType.SCOUT)) {
                return new CreateAction(UnitType.SCOUT);
            }
        } else if(game.getCurrentPlayer().getUnits(UnitType.BALISTA).size() < 1) {
            if(Rules.checkCreate(game, UnitType.BALISTA)) {
                return new CreateAction(UnitType.BALISTA);
            }
        } else if(game.getCurrentPlayer().getUnits(UnitType.ENGINEER).size() < 1) {
            if(Rules.checkCreate(game, UnitType.ENGINEER)) {
                return new CreateAction(UnitType.ENGINEER);
            }
        } else if(game.getCurrentPlayer().getUnits(UnitType.DWARF).size() < 1) {
            if(Rules.checkCreate(game, UnitType.DWARF)) {
                return new CreateAction(UnitType.DWARF);
            }
        } else if(game.getCurrentPlayer().getUnits(UnitType.ENGINEER).size() < 3) {
            if(Rules.checkCreate(game, UnitType.ENGINEER)) {
                return new CreateAction(UnitType.ENGINEER);
            }
        } else if(game.getCurrentPlayer().getUnits(UnitType.SCOUT).size() < 6) {
            if(Rules.checkCreate(game, UnitType.SCOUT)) {
                return new CreateAction(UnitType.SCOUT);
            }
        }
        
        return null;
    }
}
