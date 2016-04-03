package rules;

import models.*;
import models.units.*;

import java.util.LinkedList;
import java.util.List;

public class Simulator {
    private static int uid = 0;
    private static int getUid() {
        uid++;
        uid %= 2e9;
        return uid;
    }
    public static Game simulateAttack(Game g, int attackerId, int x, int y) {
        Game game = g.clone();
        Unit attacker = game.getCurrentPlayer().getUnit(attackerId);
        attacker.setActions(attacker.getActions() - 2);
        Unit target = game.getWorld().getCell(x, y).getUnit();

        int deltaAttacker = attacker.getStrength() - target.getDefense();
        int deltaTarget = target.getStrength() - attacker.getDefense();

        if (deltaAttacker < deltaTarget) {
            attacker.setHealth(attacker.getHealth() - 3);
        } else if (deltaTarget < deltaAttacker)  {
            target.setHealth(target.getHealth() - 3);
        } else {
            attacker.setHealth(attacker.getHealth() - 1);
            target.setHealth(target.getHealth() - 1);
        }

        if (target.getHealth() <= 0) {
            game.getWorld().getCell(x, y).setUnit(-1);
            game.getOtherPlayer().getUnits().remove(target.getId());
            attacker.setHealth(Math.max(1, attacker.getHealth()));
        } else {
            game.getWorld().getCell(attacker.getX(), attacker.getY()).setUnit(-1);
            game.getCurrentPlayer().getUnits().remove(attacker.getId());
        }

        return game;
    }

    public static Game simulateBuild(Game g, int builderId, Building building) {
        Game game = g.clone();
        Unit builder = game.getCurrentPlayer().getUnit(builderId);
        builder.setActions(builder.getActions() - 2);

        int cost = EntityInfo.getBuildingCost(building);
        game.getCurrentPlayer().setGold(game.getCurrentPlayer().getGold() - cost);

        game.getWorld().getCell(builder.getX(), builder.getY()).setBuilding(building);

        return game;
    }

    public static Game simulateCreate(Game g, UnitType unitType) {
        Game game = g.clone();
        Cell city = game.getCurrentPlayer().getCity();
        Unit u = createUnit(unitType, city.getX(), city.getY());
        game.getCurrentPlayer().getUnits().put(u.getId(), u);
        city.setUnit(u);

        int cost = city.getUnit().getUnitType().cost;
        game.getCurrentPlayer().setGold(game.getCurrentPlayer().getGold() - cost);

        return game;
    }

    public static Game simulateDestroy(Game g, int unitId) {
        Game game = g.clone();
        Unit builder = game.getCurrentPlayer().getUnit(unitId);
        builder.setActions(builder.getActions() - 2);

        game.getWorld().getCell(builder.getX(), builder.getY()).setBuilding(Building.NONE);

        return game;
    }

    public static Game simulateMove(Game g, int unitId, int x, int y) {
        Game game = g.clone();

        Unit unit = game.getCurrentPlayer().getUnit(unitId);

        game.getWorld().getCell(unit.getX(), unit.getY()).setUnit(-1);
        game.getWorld().getCell(x, y).setUnit(unit.getId());
        game.getWorld().getCell(x, y).setOwner(game.getCurrentPlayerId());

        unit.setX(x);
        unit.setY(y);

        return game;
    }

    public static Game simulateEndOfTurn(Game g) {
        Game game = g.clone();
        game.swapPlayerRoles();

        int currentPlayerId = game.getCurrentPlayerId();
        int size = game.getWorld().getSize();
        Cell[][] map = game.getWorld().getMap();

        int count = 0;
        for(int x = 0; x < size; x++) {
            for(int y = 0; y < size; y++) {
                Cell curCell = map[x][y];
                if(curCell.getOwnerId() == currentPlayerId) {
                    count ++;
                    if (curCell.getBuilding() == Building.HOSPITAL && curCell.getUnit() != null) {
                        Unit u = curCell.getUnit();
                        u.setHealth(u.getUnitType().maxHealth);
                    }
                }
            }
        }

        Player curP = game.getCurrentPlayer();
        curP.setGold(curP.getGold() + 5 * count);

        return game;
    }

    private static Unit createUnit(UnitType unitType, int x, int y) {
        int id = getUid();
        if(unitType == UnitType.ARCHER) {
            return new Archer(x, y, id);
        } else if(unitType == UnitType.BALISTA) {
            return new Balista(x, y, id);
        } else if(unitType == UnitType.DWARF) {
            return new Dwarf(x, y, id);
        } else if(unitType == UnitType.ENGINEER) {
            return new Engineer(x, y, id);
        } else if(unitType == UnitType.PALADIN) {
            return new Paladin(x, y, id);
        } else if(unitType == UnitType.PEASANT) {
            return new Peasant(x, y, id);
        } else if(unitType == UnitType.SCOUT) {
            return new Scout(x, y, id);
        } else if(unitType == UnitType.SOLDIER) {
            return new Soldier(x, y, id);
        } else {
            System.err.println("This UnitType is unknown!");
            return new Peasant(x, y, id);
        }
    }
}
