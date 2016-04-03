package algorithms.behaviours;

import models.Game;
import rules.Action;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by helio on 03/04/16.
 */
public class BehaviourOffensiveSmall implements BehaviourInterface {
        @Override
        public List<Action> decideActions(Game game) {
                LinkedList<Action> operations = new LinkedList<>();
                return operations;
        }
}
