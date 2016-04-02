package algorithms;

import io.Action;
import models.Cell;
import models.Game;
import models.Unit;

import java.util.List;


public interface FindPathInterface {
    List<Action> evaluatePath(Game game, Unit unitToMove, Cell destination);
}
