package algorithms;

import models.Building;
import models.Cell;
import models.Land;
import models.UnitType;

public class Utils {
    public static int infiniteDistance(int x1, int y1, int x2, int y2){
        return Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2));
    }

    public static int checkMove(Cell cellToReach, int xUnit,int yUnit, UnitType unitType, int availableActions) {
        if (cellToReach == null)
            return -1;
        if (Utils.infiniteDistance(cellToReach.getX(), cellToReach.getY(), xUnit,
                yUnit) != 1)
            return -1;
        if (cellToReach.getUnit() != null)
            return -1;
        if (cellToReach.getLand() == Land.RIVER
                && unitType != UnitType.ENGINEER)
            return -1;
        if (cellToReach.getLand() == Land.MONTAIN && cellToReach.getBuilding() != Building.ROAD)
            return availableActions - 4;
        if (cellToReach.getBuilding() == Building.ROAD)
            return availableActions - 1;
        return availableActions - 2;
    }
}
