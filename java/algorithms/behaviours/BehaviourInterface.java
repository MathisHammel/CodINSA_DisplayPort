package algorithms.behaviours;


import rules.Action;
import models.Game;

import java.util.List;

public interface BehaviourInterface {
    // décrit le comportemnt de toute les unité dans un ocntexte précis
    // offensif / defensif / small / medium  / large
    List<Action> decideActions(Game game);


}
