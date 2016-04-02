package io.actions;

import io.Action;
import models.Game;
import models.Unit;

/**
 * Attaque un autre personnage.
 * attackerId Unité qui doit attaquer
 * x Coordonnée x à attaquer
 * y Coordonnée y à attaquer
 */
public class AttackAction implements Action {
    int attackerId;
    int x;
    int y;

    public AttackAction(Unit attacker, int x, int y) {
        this.attackerId = attacker.getId();
        this.x = x;
        this.y = y;
    }

    public AttackAction(Unit attacker, Unit target) {
        this.attackerId = attacker.getId();
        this.x = target.getX();
        this.y = target.getY();
    }

    public String serialize() {
        return "A,"+this.attackerId+","+this.x+","+this.y;
    }

    @Override
    public boolean check(Game game) {
        return false;
    }
}
