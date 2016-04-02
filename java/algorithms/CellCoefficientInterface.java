package algorithms;

import models.Cell;
import models.Game;


public interface CellCoefficientInterface {
    public int evaluate(Game game, Cell cellToEvaluate);
}
