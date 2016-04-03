package algorithms.evaluations;

import models.Cell;
import models.Game;

import java.util.Map;

public class TerritoryEvaluation implements EvaluationInterface{
    @Override
    public double evaluate(Game g) {
        Cell[][] map = g.getWorld().getMap();

        return 0.0;
    }
}
