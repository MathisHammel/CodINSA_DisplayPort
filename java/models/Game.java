package models;

import algorithms.Utils;

public class Game {
    public World world;
    public Player us;
    public Player them;
    public Player currentPlayer;
    private int roundNumber;

    public Game () {
        us = new Player(0);
        them = new Player(1);
        currentPlayer = us;
        roundNumber = 0;
    }

    public void nextRound() {
        /*
        currentPlayer = otherPlayer();
        currentPlayer.gainGold(world);
        // creation
        char newUnitType = 'E';
        currentPlayer.createUnit(newUnitType);
        // attack
        currentPlayer.attack(world, otherPlayer(), 0, 1, 1); // make the first unit attack (1, 1)

        // move
        int newAction = checkMove(0, 1, 1);  // format idUnit x y
        if (newAction >= 0) {
            currentPlayer.moveUnit(world, 0, 1, 1, newAction); // move the first unit to (1, 1)

        }

        heal();  // interaction between hospitals-units and action regeneration
        */
    }

    private void heal() {
        for (int i: currentPlayer.units.keySet()) {
            Unit unit =currentPlayer.units.get(i);
            if (world.getCell(unit.getX(), unit.getY()).building == Building.HOSPITAL)
                unit.setHealth(unit.getMaxHealth());
            unit.setActions(unit.getMaxActions());
        }
    }

    private Player otherPlayer() {
        if (currentPlayer == us)
            return them;
        return us;
    }

}
