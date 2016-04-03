
package algorithms;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import models.Cell;
import models.Game;
import models.Unit;
import rules.Action;
import rules.UnitType;
import rules.actions.MoveAction;

public class FindPathExploration implements FindPathInterface
{

    @Override
    public  List<Action> evaluatePath(Game game, Unit nullUnit, Cell nullDest) {
        LinkedList<Action> operations = new LinkedList<>();

        for(Map.Entry<Integer, Unit> entry : game.getCurrentPlayer().getUnits(UnitType.SCOUT).entrySet()) {
            Unit unitToMove = entry.getValue();

            Map<Cell, Unit.ReachableResult> reachableCells = unitToMove.getReachableCells(game.getWorld());
            Cell best = null;
            int bestScore = Integer.MIN_VALUE;
            for(Cell candidate : reachableCells.keySet()) {
                double weight;
                if(candidate.getOwner() == game.getOurPlayer()) weight = 0.1;
                else if(candidate.getOwner() == game.getTheirPlayer()) weight = 2;
                else weight = 1;

                if(bestScore < weight * (candidate.getScore()+0.15)) {
                    best = candidate;
                    bestScore = (int) (weight * (candidate.getScore()+0.15));
                }
            }

            if(best != null) {
                // il faut reconstituer le chemin
                while(best != game.getWorld().getCell(unitToMove.getX(), unitToMove.getY())){
                    operations.addFirst(new MoveAction(unitToMove, best.getX(), best.getY()));
                    best = reachableCells.get(best).from;
                }
            }
        }

        return operations;
    }

}
