package algorithms;

import models.Cell;
import models.Game;


public class CellScoreOnCitiesDistance implements CellScoreInterface {
    @Override
    public int evaluate(Game game, Cell cellToEvaluate) {
        Cell ourCity = game.getOurPlayer().getCity();
        Cell theirCity = game.getTheirPlayer().getCity();
        int cityDistance = Utils.infiniteDistance(ourCity.getX(), ourCity.getY(), theirCity.getX(), theirCity.getY());
        int cellToOurCity = Utils.infiniteDistance(ourCity.getX(), ourCity.getY(), cellToEvaluate.getX(), cellToEvaluate.getY());
        int cellToEnemyCity = Utils.infiniteDistance(theirCity.getX(), theirCity.getY(), cellToEvaluate.getX(), cellToEvaluate.getY());
        return cellToOurCity + cellToEnemyCity - cityDistance;
    }
}
