package rules.actions;

import rules.Action;
import models.Game;

public class EndOfTurnAction implements Action {
    public EndOfTurnAction() {}

    public String serialize() {
        return "E";
    }

    @Override
    public boolean check(Game game) {
        return true;
    }
}
