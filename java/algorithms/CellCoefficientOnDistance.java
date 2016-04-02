package algorithms;

import models.Cell;

/**
 * Created by loict on 02/04/2016.
 */
public class CellCoefficientOnDistance implements CellCoefficientInterface {
    @Override
    public int evaluate(Cell ourCity, Cell enemyCity, Cell cellToEvaluate) {
        int cityDistance = Utils.infiniteDistance(ourCity.x, ourCity.y, enemyCity.x, enemyCity.y);
        int cellToOurCity = Utils.infiniteDistance(ourCity.x, ourCity.y, cellToEvaluate.x, cellToEvaluate.y);
        int cellToEnemyCity = Utils.infiniteDistance(enemyCity.x, enemyCity.y, cellToEvaluate.x, cellToEvaluate.y);
        return cellToOurCity + cellToEnemyCity - cityDistance;
    }
}
