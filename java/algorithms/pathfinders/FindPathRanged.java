package algorithms.pathfinders;

import algorithms.Utils;
import algorithms.worldevaluations.OffensiveEvaluation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import models.Cell;
import models.Game;
import models.Unit;
import rules.Action;
import rules.Simulator;
import rules.actions.AttackAction;
import rules.actions.MoveAction;

public class FindPathRanged implements FindPathInterface {

    @Override
    public List<Action> evaluatePath(Game game, Unit unitToMove, Cell dropped) {
        List<Action> actions = new ArrayList<>();
        
        List<Cell> attackable = unitToMove.attackable();
        List<Unit> enemiesAttackable = new ArrayList<>();
        for(Unit u : game.getOtherPlayer().getUnits().values()) {
            if(attackable.contains(game.getWorld().getCell(u.getX(), u.getY()))) {
                enemiesAttackable.add(u);
            }
        }
        if(!enemiesAttackable.isEmpty()) {
            final Cell city = game.getCurrentPlayer().getCity();

            Collections.sort(enemiesAttackable, (Unit u1, Unit u2) -> {
            return Utils.infiniteDistance(u1.getX(), u1.getY(), city.getX(), city.getY())
                 - Utils.infiniteDistance(u2.getX(), u2.getY(), city.getX(), city.getY());
            });
            
            actions.add(new AttackAction(unitToMove, enemiesAttackable.get(0)));
            return actions;
        }
        
        Cell bestWay = null; //do not move
        double score = new OffensiveEvaluation().evaluate(game);
        
        for(Cell c : unitToMove.getReachableCells(game.getWorld()).keySet()) {
            Game newGame = Simulator.simulateMove(game, unitToMove.getId(), c.getX(), c.getY());
            double scoreC = new OffensiveEvaluation().evaluate(newGame);
            
            if(scoreC > score) {
                bestWay = c;
                score = scoreC;
            }
        }
        
        if(bestWay != null) {
            actions.addAll(new FindPathByClosest().evaluatePath(game, unitToMove, bestWay));
        }
        
        return actions;
    }
    
}
