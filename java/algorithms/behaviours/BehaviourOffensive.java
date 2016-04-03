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

public class BehaviourOffensive implements BehaviourInterface {
    @Override
    public List<Action> decideActions(Game game) {
        // Bouger et attaquer les unité possibles
        LinkedList<Action> operations = new LinkedList<>();


        // attaquer sans bouger tout le monde (sauf scout et ingé)


        //bouger/attaquer tout le monde (sauf scout)
        FindPathInterface findPathOff = new FindPathOffensive();
        for(Map.Entry<Integer, Unit> entry : game.getCurrentPlayer().getUnits(UnitType.SOLDIER).entrySet()) {
            operations.addAll(findPathOff.evaluatePath(game, entry.getValue(), null));
        }
        return operations;
    }
}
