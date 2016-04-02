package algorithms;


import io.Action;
import models.Game;

import java.util.List;

public interface BehaviourInterface {
    List<Action> decideActions(Game game);


}
