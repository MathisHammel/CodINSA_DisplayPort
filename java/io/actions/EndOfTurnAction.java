package io.actions;

import io.Action;
import models.Game;
import models.Unit;

public class EndOfTurnAction implements Action {
    public EndOfTurnAction() {}

    public String serialize() {
        return "E";
    }

    @Override
    public boolean check(Game game) {
        return false;
    }
}
