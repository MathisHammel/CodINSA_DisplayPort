package algorithms;

import models.Cell;
import models.Game;


public interface CellScoreInterface {
    public int evaluate(Game game, Cell cellToEvaluate);
}
