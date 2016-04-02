package algorithms;

import models.Cell;
import models.Game;
import models.Unit;

import java.util.List;


public interface FindPathInterface {
    List<String> evaluatePath(Game game, Unit unitToMove, Cell destination);
}
