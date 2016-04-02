package io;

import models.Game;

public interface Action {
    String serialize();
    boolean check(Game game);
}
