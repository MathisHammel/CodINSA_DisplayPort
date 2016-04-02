package io.actions;

import io.Action;
import models.Unit;

public class EndOfTurnAction implements Action {
    public EndOfTurnAction() {}

    public String serialize() {
        return "E";
    }
}
