package models;

public abstract class GameEntity {
    private Game game;

    GameEntity(Game game) {
        this.game = game;
    }

    Game getGame() {
        return this.game;
    }

    GameEntity setGame(Game game) {
        this.game = game;
        return this;
    }
}
