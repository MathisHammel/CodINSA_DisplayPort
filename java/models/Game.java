package models;

import java.util.LinkedList;
import java.util.List;
import algorithms.Utils;

public class Game {
    private World world;
    private List<Player> players;
    private int currentPlayer;
    private int roundNumber;

    public Game () {
        players = new LinkedList<>();
        currentPlayer = 0;
        roundNumber = 0;
    }

    public void nextRound() {
        currentPlayer += 1;
        currentPlayer %= players.size();
        players.get(currentPlayer).gainGold(world);
        // creation
        char newUnitType = 'E';
        players.get(currentPlayer).createUnit(newUnitType);
        // attack


        // move
        int newAction = checkMove(0, 1, 1);  // format idUnit x y
        if (newAction >= 0) {
            players.get(currentPlayer).moveUnit(world, 0, 1, 1, newAction); // move the first unit to (1, 1)

        }

        heal();  // interaction between hospitals-units and action regeneration
    }

    private int checkMove(int idUnit, int x, int y) {
        if (world.getCell(x, y) == null)
            return -1;
        if (Utils.infiniteDistance(x, y, players.get(currentPlayer).units.get(idUnit).getX(),
                players.get(currentPlayer).units.get(idUnit).getY()) != 1)
            return -1;
        if (world.getCell(x, y).land == Land.RIVER
                && players.get(currentPlayer).units.get(idUnit).getUnitType() != UnitType.ENGINEER)
            return -1;
        if (world.getCell(x, y).land == Land.MONTAIN && world.getCell(x, y).building != Building.ROAD)
            return players.get(currentPlayer).units.get(idUnit).getActions() - 4;
        if (world.getCell(x, y).building == Building.ROAD)
            return players.get(currentPlayer).units.get(idUnit).getActions() - 1;
        return players.get(currentPlayer).units.get(idUnit).getActions() - 2;
    }

    private void heal() {
        for (int i: players.get(currentPlayer).units.keySet()) {
            Unit unit = players.get(currentPlayer).units.get(i);
            if (world.getCell(unit.getX(), unit.getY()).building == Building.HOSPITAL)
                unit.setHealth(unit.getMaxHealth());
            unit.setActions(unit.getMaxActions());
        }
    }


}
