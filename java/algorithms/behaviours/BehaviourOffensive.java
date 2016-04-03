package algorithms.behaviours;

import algorithms.pathfinders.FindPathExploration;
import algorithms.pathfinders.FindPathInterface;
import algorithms.pathfinders.FindPathOffensive;
import models.Game;
import rules.Action;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import models.Unit;
import rules.UnitType;
import rules.actions.AttackAction;

public class BehaviourOffensive implements BehaviourInterface {
    @Override
    public List<Action> decideActions(Game game) {
        // Bouger et attaquer les unité possibles
        LinkedList<Action> operations = new LinkedList<>();

        // bouger vers les enemis/attaquer tout le monde (sauf scout et ingé)
        // soldier, peasan, dwarf, paladin
        FindPathInterface findPathOff = new FindPathOffensive();
        for(Map.Entry<Integer, Unit> entry : game.getCurrentPlayer().getUnits(UnitType.SOLDIER).entrySet()) {
            operations.addAll(findPathOff.evaluatePath(game, entry.getValue(), null));
            if(operations.getLast() instanceof AttackAction){
                // Si on a fini sur une attack action on veut effectuer les action tout de suite.
                return operations;
            }
        }
        for(Map.Entry<Integer, Unit> entry : game.getCurrentPlayer().getUnits(UnitType.PEASANT).entrySet()) {
            operations.addAll(findPathOff.evaluatePath(game, entry.getValue(), null));
            if(operations.getLast() instanceof AttackAction){
                return operations;
            }
        }
        for(Map.Entry<Integer, Unit> entry : game.getCurrentPlayer().getUnits(UnitType.DWARF).entrySet()) {
            operations.addAll(findPathOff.evaluatePath(game, entry.getValue(), null));
            if(operations.getLast() instanceof AttackAction){
                return operations;
            }
        }
        for(Map.Entry<Integer, Unit> entry : game.getCurrentPlayer().getUnits(UnitType.PALADIN).entrySet()) {
            operations.addAll(findPathOff.evaluatePath(game, entry.getValue(), null));
            if(operations.getLast() instanceof AttackAction){
                return operations;
            }
        }
        return operations;
    }
}
