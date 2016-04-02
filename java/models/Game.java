package models;

import algorithms.Utils;

public class Game {
    public static int OUR_ID = 0;

    private World world;
    private Player[] players;
    private int currentPlayerId;
    private int roundNumber;

    public Game () {
        this.players = new Player[]{
            new Player(0),
            new Player(1)
        };
        this.roundNumber = 0;
        this.world = new World(5);
        this.currentPlayerId = 0;
    }

    public Game(Player us, Player them, int currentPlayerId, Cell[][] map) {
        this.players[OUR_ID] = us;
        this.players[1 - OUR_ID] = them;
        this.currentPlayerId = currentPlayerId;
        this.world = new World(map.length, map);
    }

    public Player getPlayer(int id) {
        return this.players[id];
    }

    public Player getCurrentPlayer() {
        return this.players[this.currentPlayerId];
    }

    public Player getOtherPlayer() {
        return this.players[1 - this.currentPlayerId];
    }

    public Player getOurPlayer() {
        return this.players[OUR_ID];
    }

    public Player getTheirPlayer() {
        return this.players[1 - OUR_ID];
    }

    public World getWorld() {
        return this.getWorld();
    }

    public void nextRound() {
        this.currentPlayerId = 1 - currentPlayerId;
        Player currentPlayer = this.getCurrentPlayer();

        currentPlayer.gainGold(world);

        // creation
        char newUnitType = 'E';
        currentPlayer.createUnit(newUnitType);
        // attack
        currentPlayer.attack(world, this.getOtherPlayer(), 0, 1, 1); // make the first unit attack (1, 1)

        // move
        int newAction = checkMove(0, 1, 1);  // format idUnit x y
        if (newAction >= 0) {
            currentPlayer.moveUnit(world, 0, 1, 1, newAction); // move the first unit to (1, 1)
        }

        heal();  // interaction between hospitals-units and action regeneration
    }

    // return the cost to move to x,y (-1 if impossible)
    private int checkMove(int idUnit, int x, int y) {
        Player currentPlayer = this.getCurrentPlayer();

        // The cell is out of bounds
        if (world.getCell(x, y) == null){
            return -1;
        }

        // The cell is not a neighbour cell
        if (Utils.infiniteDistance(x, y, currentPlayer.getUnit(idUnit).getX(), currentPlayer.getUnit(idUnit).getY()) != 1) {
            return -1;
        }

        // The unit cannot access the cell
        if (world.getCell(x, y).getLand() == Land.RIVER && currentPlayer.getUnit(idUnit).getUnitType() != UnitType.ENGINEER) {
            return -1;
        }

        //
        if (world.getCell(x, y).getLand() == Land.MONTAIN && world.getCell(x, y).getBuilding() != Building.ROAD) {
            return currentPlayer.getUnit(idUnit).getActions() - 4;
        }

        if (world.getCell(x, y).getBuilding() == Building.ROAD){
            return currentPlayer.getUnit(idUnit).getActions() - 1;
        }

        return currentPlayer.getUnit(idUnit).getActions() - 2;
    }

    private void heal() {
        Player currentPlayer = this.getCurrentPlayer();
        for (int i: currentPlayer.getUnits().keySet()) {
            Unit unit = currentPlayer.getUnit(i);
            if (world.getCell(unit.getX(), unit.getY()).getBuilding() == Building.HOSPITAL){
                unit.setHealth(unit.getMaxHealth());
            }
            unit.setActions(unit.getMaxActions());
        }
    }

    public Game clone() {
        World w = this.world.clone();
        return new Game();
    }
}
