package models;

import java.util.LinkedList;
import java.util.List;

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
        // some actions
        int newAction = checkMove(currentPlayer, 0, 1, 1);
        if (newAction >= 0)
            players.get(currentPlayer).moveUnit(0, 1, 1, newAction); // move the first unit to (1, 1)
        

    }

    private int checkMove(int idPlayer, int idUnit, int x, int y) {
        if (world.getCell(x, y) == null)
            return -1;
        if (Math.max(Math.abs(players.get(idPlayer).units.get(idUnit).getX() - x ),
                Math.abs(players.get(idPlayer).units.get(idUnit).getY() - y)) != 1)
            return -1;
        if (world.getCell(x, y).land == Land.RIVER && players.get(idPlayer).units.get(idUnit).getUnitType() != UnitType.ENGINEER)
            return -1;
        if (world.getCell(x, y).land == Land.MONTAIN && world.getCell(x, y).building != Building.ROAD)
            return players.get(idPlayer).units.get(idUnit).getActions() - 4;
        if (world.getCell(x, y).building == Building.ROAD)
            return players.get(idPlayer).units.get(idUnit).getActions() - 1;
        return players.get(idPlayer).units.get(idUnit).getActions() - 2;
    }
}
