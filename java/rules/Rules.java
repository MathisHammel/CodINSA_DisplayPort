package rules;

import algorithms.Utils;
import models.*;

public class Rules {

    public static boolean checkAttack(){
        return false;
    }

    public static boolean checkBuild(Game game){
        return false;
    }

    public static boolean checkCreate(Game game, UnitType unitType) {
        Cell city = game.getCurrentPlayer().getCity();
        if (city == null) {
            System.err.println("Cannot create: city not found");
            return false;
        }
        Unit unit = game.getWorld().getCell(city.getX(), city.getY()).getUnit();
        if (unit != null) {
            System.err.println("Cannot create: city is full");
            return false;
        }
        if(game.getCurrentPlayer().getGold() < unitType.cost) {
            System.err.println("Cannot create: unit is too expensive");
            return false;
        }
        return true;
    }

    public static boolean checkDestroy(Game game, int unitId) {
        Unit unit = game.getCurrentPlayer().getUnit(unitId);
        if (unit == null) {
            System.err.println("Cannot destroy: unit " + unitId + " not found");
            return false;
        }
        if(unit.getUnitType() != UnitType.ENGINEER) {
            System.err.println("Cannot destroy: not an engineer");
            return false;
        }
        Cell target = game.getWorld().getCell(unit.getX(), unit.getY());
        if (target == null) {
            System.err.println("Cannot destroy: cell not found");
            return false;
        }
        Building building = target.getBuilding();
        if (building == Building.NONE) {
            System.err.println("Cannot destroy: not building");
            return false;
        }
        if(unit.getActions() < 2) {
            System.err.println("Cannot destroy: not enough actions");
            return false;
        }
        return true;
    }

    public static boolean checkMove(Game game, int unitId, int x, int y) {
        Unit unit = game.getCurrentPlayer().getUnit(unitId);
        if (unit == null) {
            System.err.println("Cannot move: unit " + unitId + " not found");
            return false;
        }
        if (!isCellAccessible(game, unit.getUnitType(), x, y)) {
            System.err.println("Cannot move: cell is not accessible");
            return false;
        }
        int cost = getCellCost(game, game.getWorld().getCell(x, y));
        if (cost > unit.getActions()) {
            System.err.println("Cannot move: not enough actions");
            return false;
        }
        return true;
    }

    public static boolean isCellAccessible(Game game, UnitType unitType, int x, int y) {
        Cell target = game.getWorld().getCell(x, y);
        if (target == null) {
            // Cell is out of bounds
            return false;
        }
        if(target.getUnit() != null) {
            return false; // Cannot move: cell is full
        }
        if (target.getLand() == Land.RIVER && target.getBuilding() == Building.BRIDGE && unitType != UnitType.ENGINEER) {
            return false; // RIVER IS NOT ACCESSIBLE
        }
        return false;
    }

    public static int getCellCost(Game game, Cell target) {
        int cost = target.getLand() == Land.MONTAIN ? 4 : 2;
        if(target.getBuilding() == Building.ROAD) {
            cost /= 2;
        }
        return cost;
    }

}
