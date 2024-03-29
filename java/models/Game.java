package models;

import algorithms.Utils;
import rules.UnitType;

public class Game {
    public static int OUR_ID = -1;
    public static int PLAYER0_CITY_X = -1;
    public static int PLAYER0_CITY_Y = -1;
    public static int PLAYER1_CITY_X = -1;
    public static int PLAYER1_CITY_Y = -1;

    protected World world;
    protected Player[] players = new Player[2];
    protected int currentPlayerId;
    protected int roundNumber;

    public Game () {
        this(new Player(0), new Player(1), 0, (World) null);
    }

    public Game(Player player0, Player player1, int currentPlayerId, Cell[][] map) {
        this(player0, player1, currentPlayerId, new World(map));
    }

    public Game(Player player0, Player player1, int currentPlayerId, World world) {
        this(player0, player1, currentPlayerId, world, 0);
    }

    public Game(Player player0, Player player1, int currentPlayerId, World world, int roundNumber) {
        this.players[0] = player0;
        this.players[1] = player1;
        player0.setCity(world.getCell(PLAYER0_CITY_X, PLAYER0_CITY_Y));
        player1.setCity(world.getCell(PLAYER1_CITY_X, PLAYER1_CITY_Y));
        this.currentPlayerId = currentPlayerId;
        this.world = world;
        this.roundNumber = roundNumber;
        this.bindGame();
    }

    public Game clone() {
        World world = this.world.clone();
        return new Game(this.getPlayer(0).clone(), this.getPlayer(1).clone(), this.currentPlayerId, world, this.roundNumber);
    }

    public void bindGame() {
        this.players[0].bindGame(this);
        this.players[1].bindGame(this);
        this.world.bindGame(this);
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

    public int getCurrentPlayerId(){
        return this.currentPlayerId;
    }

    public int getOtherPlayerId(){
        return 1 - this.currentPlayerId;
    }

    public Game swapPlayerRoles() {
        this.currentPlayerId = 1 - this.currentPlayerId;
        return this;
    }

    public World getWorld() {
        return this.world;
    }

    public Unit getOurUnit(int unitId) {
        return this.getOurPlayer().getUnit(unitId);
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
}
