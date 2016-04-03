package algorithms.globalEvaluations;

import algorithms.Utils;
import models.Cell;
import models.Game;
import models.Player;
import models.Unit;
import rules.UnitType;

import java.util.Map;

public class DefensiveEvaluation implements GlobalEvaluationInterface {
    @Override
    public double evaluate(Game game) {
        double engineerDanger = 0.0;
        double otherDanger = 0.0;

        Player otherPlayer = game.getOtherPlayer();
        Map<Integer, Unit> units = otherPlayer.getUnits();
        Cell curCity = game.getCurrentPlayer().getCity();
        int size = game.getWorld().getSize();

        for(int id: units.keySet()) {
            Unit otherUnit = units.get(id);
            if (otherUnit.getUnitType() == UnitType.ENGINEER) {
                engineerDanger += 3.0;
                double dist = Utils.infiniteDistance(curCity.getX(), curCity.getY(), otherUnit.getX(), otherUnit.getY());
                engineerDanger += 2 * (2*size - dist);
            } else {
                otherDanger += otherUnit.getStrength() / 2 + otherUnit.getDefense() / 3;
            }
        }

        return - engineerDanger - otherDanger;
    }
}
