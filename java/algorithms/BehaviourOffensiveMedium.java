package algorithms;

import models.Cell;
import models.Game;
import models.Unit;
import rules.Action;
import rules.UnitType;
import rules.actions.MoveAction;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BehaviourOffensiveMedium implements BehaviourInterface {
    @Override
    public List<Action> decideActions(Game game) {
        // Bouger et attaquer les unité possibles
        LinkedList<Action> operations = new LinkedList<>();

        // attaquer tout le monde (sauf scout et ingé)


        //bouger/attaquer tout le monde (sauf scout)




        // Déplacement des Scouts
        FindPathInterface findPathScout = new FindPathExploration();
        operations.addAll(findPathScout.evaluatePath(game, null, null));
        return operations;
    }
}
