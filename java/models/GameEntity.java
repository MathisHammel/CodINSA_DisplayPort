package models;

public abstract class GameEntity {
    protected Game game = null;

    protected GameEntity(Game game) {
        this.game = game;
    }

    protected Game getGame() {
        return this.game;
    }

    protected GameEntity setGame(Game game) {
        this.game = game;
        return this;
    }

    protected abstract void bindGame(Game game);
}
