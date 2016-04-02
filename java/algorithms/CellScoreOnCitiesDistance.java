package algorithms;

import models.Cell;
import models.Game;


public class CellScoreOnCitiesDistance implements CellScoreInterface {
    @Override
    public int evaluate(Game game, Cell cellToEvaluate) {
        Cell ourCity = game.us.city;
        Cell enemyCity = game.them.city;
        int cityDistance = Utils.infiniteDistance(ourCity.x, ourCity.y, enemyCity.x, enemyCity.y);
        int cellToOurCity = Utils.infiniteDistance(ourCity.x, ourCity.y, cellToEvaluate.x, cellToEvaluate.y);
        int cellToEnemyCity = Utils.infiniteDistance(enemyCity.x, enemyCity.y, cellToEvaluate.x, cellToEvaluate.y);
        return cellToOurCity + cellToEnemyCity - cityDistance;
    }
}
