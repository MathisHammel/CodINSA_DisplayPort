
package algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import models.Cell;
import models.Game;
import models.Unit;
import rules.Action;
import rules.UnitType;
import rules.actions.MoveAction;

public class BehaviourExploration implements BehaviourInterface {

    @Override
    public List<Action> decideActions(Game game) {
        LinkedList<Action> operations = new LinkedList<>();
        
        for(Map.Entry<Integer, Unit> entry : game.getCurrentPlayer().getUnits(UnitType.SCOUT).entrySet()) {
            Unit unitToMove = entry.getValue();
            
            Map<Cell, Unit.ReachableResult> reachableCells = unitToMove.getReachableCells(game.getWorld());
            Cell best = null;
            int bestScore = Integer.MIN_VALUE;
            for(Cell candidate : reachableCells.keySet()) {
                double weight;
                if(candidate.getOwner() == game.getOurPlayer().getId()) weight = 0.5;
                else if(candidate.getOwner() == game.getTheirPlayer().getId()) weight = 1.5;
                else weight = 1;
                
                if(bestScore < weight * candidate.getScore()) {
                    best = candidate;
                    bestScore = (int) (weight * candidate.getScore());
                }
            }
            
            // il faut reconstituer le chemin
            while(best != game.getWorld().getCell(unitToMove.getX(), unitToMove.getY())){
                operations.addFirst(new MoveAction(unitToMove, best.getX(), best.getY()));
                best = reachableCells.get(best).from;
            }
        }
        
        return operations;
    }
    
}
