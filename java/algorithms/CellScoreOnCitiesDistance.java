package algorithms;

import models.Cell;
import models.Game;


public class CellScoreOnCitiesDistance implements CellScoreInterface {
    @Override
    public int evaluate(Game game, Cell cellToEvaluate) {
        Cell ourCity = game.us.city;
        Cell enemyCity = game.them.city;
        int cityDistance = Utils.infiniteDistance(ourCity.getX(), ourCity.getY(), enemyCity.getX(), enemyCity.getY());
        int cellToOurCity = Utils.infiniteDistance(ourCity.getX(), ourCity.getY(), cellToEvaluate.getX(), cellToEvaluate.getY());
        int cellToEnemyCity = Utils.infiniteDistance(enemyCity.getX(), enemyCity.getY(), cellToEvaluate.getX(), cellToEvaluate.getY());
        return cellToOurCity + cellToEnemyCity - cityDistance;
    }
}
