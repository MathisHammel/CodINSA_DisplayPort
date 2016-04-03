package algorithms;

import algorithms.behaviours.BehaviourInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import models.Cell;
import models.Game;
import models.Unit;
import rules.Action;
import rules.UnitType;
import rules.actions.AttackAction;
import rules.actions.MoveAction;

public class BehaviourOffensive implements BehaviourInterface {

    @Override
    public List<Action> decideActions(Game game) {
        LinkedList<Action> operations = new LinkedList<>();
        
        for(Map.Entry<Integer, Unit> entry : game.getCurrentPlayer().getUnits(UnitType.SOLDIER).entrySet()) {
            Unit myUnit = entry.getValue();
            
            Cell cell = chooseCell(null, game, UnitType.ENGINEER);
            cell = chooseCell(cell, game, UnitType.DWARF);
            cell = chooseCell(cell, game, UnitType.PALADIN);
            cell = chooseCell(cell, game, UnitType.SOLDIER);
            cell = chooseCell(cell, game, UnitType.PEASANT);
            cell = chooseCell(cell, game, UnitType.SCOUT);
            cell = chooseCell(cell, game, UnitType.BALISTA);
            cell = chooseCell(cell, game, UnitType.ARCHER);
            
            Map<Cell, Unit.ReachableResult> cells;
            if(cell != null && myUnit.attackable().contains(cell)) {
                operations.add(new AttackAction(myUnit, cell.getX(), cell.getY()));
            } else {
                if(cell == null) {
                    cell = game.getCurrentPlayer().getCity();
                }
                
                return new FindPathByClosest().evaluatePath(game, myUnit, cell);
            }
        }
        
        return operations;
    }
    
    private Cell chooseCell(Cell before, Game game, UnitType unit) {
        if(before != null) return before;
        
        Map<Integer, Unit> units = game.getOtherPlayer().getUnits(unit);
        if(units.isEmpty()) return null;
        
        final Cell city = game.getCurrentPlayer().getCity();
        
        List<Unit> unitList = new ArrayList<>(units.values());
        Collections.sort(unitList, (Unit u1, Unit u2) -> {
            return Utils.infiniteDistance(u1.getX(), u1.getY(), city.getX(), city.getY())
                 - Utils.infiniteDistance(u2.getX(), u2.getY(), city.getX(), city.getY());
        });
        
        return game.getWorld().getCell(unitList.get(0).getX(), unitList.get(0).getY());
    }
    
}
