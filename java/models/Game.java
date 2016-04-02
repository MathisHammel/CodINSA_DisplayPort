package models;

import algorithms.Utils;

public class Game {
    private World world;
    public Player us;
    public Player them;
    private Player currentPlayer;
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
            if (world.getCell(unit.getX(), unit.getY()).getBuilding() == Building.HOSPITAL)
                unit.setHealth(unit.getMaxHealth());
            unit.setActions(unit.getMaxActions());
        }
    }

    public Player otherPlayer() {
        if (currentPlayer == us)
            return them;
        return us;
    }

    public Game clone() {
        World w = this.world.clone();
        return new Game();
    }
}
