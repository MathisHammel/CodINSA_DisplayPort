package algorithms.behaviours;

import models.Game;
import rules.Action;

import java.util.LinkedList;
import java.util.List;

public class BehaviourDefensive implements BehaviourInterface {
        @Override
        public List<Action> decideActions(Game game) {
                LinkedList<Action> operations = new LinkedList<>();
                return operations;
        }
}
