package algorithms.globalEvaluations;

import models.Cell;
import models.Game;
import models.Player;
import models.Unit;

import java.util.List;
import java.util.Map;

public class OffensiveEvaluation implements GlobalEvaluationInterface {
    @Override
    public double evaluate(Game game) {

        Player otherPlayer = game.getOtherPlayer();
        Map<Integer, Unit> units = otherPlayer.getUnits();

        int size = game.getWorld().getSize();

        double totalDefense = 0.0;
        double totalStrength = 0.0;
        double cellCoverage = 0.0;

        for(int id: units.keySet()) {
            Unit curUnit = units.get(id);
            totalDefense += curUnit.getDefense();
            totalStrength += curUnit.getStrength();
            List<Cell> attackable = curUnit.attackable();
            int maxRange = 1+2*curUnit.getMaxRange();
            int minRange = 1+2*(curUnit.getMinRange() - 1);
            int borderDist = Math.min(curUnit.getX(), size - curUnit.getX()) + Math.min(curUnit.getY(), size - curUnit.getY());
            if(borderDist / 2 < maxRange) {
                maxRange--;
            }
            int protectedCells = maxRange*maxRange - minRange * minRange;
            protectedCells = Math.max(protectedCells, attackable.size());
            cellCoverage += curUnit.getStrength() * (protectedCells + 3 * attackable.size());
        }

        return cellCoverage;
    }
}
