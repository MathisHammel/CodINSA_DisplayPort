package models;

public abstract class GameEntity {
    public Game game = null;

    public GameEntity(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return this.game;
    }

    public GameEntity setGame(Game game) {
        this.game = game;
        return this;
    }

    public abstract void bindGame(Game game);
}
