package algorithms;

import models.Cell;
import models.Unit;
import rules.UnitType;
import rules.Rules;


public class Utils {
    public static int infiniteDistance(int x1, int y1, int x2, int y2){
        return Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2));
    }

    public static int getActionsAfterMove(Cell cellToReach, int xUnit, int yUnit, UnitType unitType, int availableActions) {
        boolean isAccessible = false;
        if(cellToReach != null)
            isAccessible = Rules.isCellAccessible(cellToReach.getGame(), unitType, cellToReach.getX(), cellToReach.getY());
        if(!isAccessible) {
            return -1;
        }
        int cost = Rules.getCellCost(cellToReach.getGame(), cellToReach);
        if (cost > availableActions) {
            return -1;
        }
        if (Utils.infiniteDistance(cellToReach.getX(), cellToReach.getY(), xUnit, yUnit) != 1){
            return -1;
        }
        return availableActions - cost;
    }

    public static boolean random(double seed, Unit unit, double x) {
        return ((double) ((10 * seed + unit.getId() + unit.getX() + unit.getY()) % 10.0) / 10.0) < x;
    }

    public static boolean random(double seed, Cell cell, double x) {
        return ((double) ((10 * seed + cell.getX() + cell.getY()) % 10.0) / 10.0) < x;
    }

}
