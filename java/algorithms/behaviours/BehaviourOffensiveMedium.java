package algorithms.behaviours;

import algorithms.FindPathExploration;
import algorithms.FindPathInterface;
import algorithms.FindPathOffensive;
import models.Game;
import rules.Action;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import models.Unit;
import rules.UnitType;

public class BehaviourOffensiveMedium implements BehaviourInterface {
    @Override
    public List<Action> decideActions(Game game) {
        // Bouger et attaquer les unité possibles
        LinkedList<Action> operations = new LinkedList<>();

        // attaquer tout le monde (sauf scout et ingé)


        //bouger/attaquer tout le monde (sauf scout)
        FindPathInterface findPathOff = new FindPathOffensive();
        for(Map.Entry<Integer, Unit> entry : game.getCurrentPlayer().getUnits(UnitType.SOLDIER).entrySet()) {
            operations.addAll(findPathOff.evaluatePath(game, entry.getValue(), null));
        }

        // Déplacement des Scouts
        FindPathInterface findPathScout = new FindPathExploration();
        operations.addAll(findPathScout.evaluatePath(game, null, null));
        return operations;
    }
}
