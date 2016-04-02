package algorithms;

import models.Cell;

/**
 * Created by loict on 02/04/2016.
 */
public interface CellCoefficientInterface {
    public int evaluate(Cell ourCity, Cell enemyCity, Cell cellToEvaluate);
}
