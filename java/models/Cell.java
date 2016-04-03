package models;

/**
 * Created by Charles on 02/04/2016.
 */
public class Cell extends GameEntity {
    protected Land land;
    protected Building building;
    protected int unit;
    protected int owner;
    protected int score;
    protected int x;
    protected int y;

    public Cell(int x, int y, Land land, Building building) { this(x, y, land, building, -1); }

    public Cell(int x, int y, Land land, Building building, int unit) {
        this(x, y, land, building, unit, -1);
    }

    public Cell(int x, int y, Land land, Building building, int unit, int owner) {
        super(null);
        this.x = x;
        this.y = y;
        this.land = land;
        this.building = building;
        this.unit = unit;
        this.owner = owner;
    }

    public void bindGame(Game g) {
        this.setGame(g);
    }

    public Land getLand() {
        return this.land;
    }

    public Building getBuilding() {
        return this.building;
    }

    public Unit getUnit() {
        return this.unit < 0 ? null : this.getOwner().getUnit(this.unit);
    }

    public Player getOwner() {
        return this.owner < 0 ? null : this.getGame().getPlayer(this.owner);
    }

    public int getOwnerId() {
        return this.owner;
    }

    public int getScore() {
        return this.score;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Cell setLand(Land land) {
        this.land = land;
        return this;
    }

    public Cell setBuilding(Building building) {
        this.building = building;
        return this;
    }

    public Cell setUnit(int unitId) {
        this.unit = unitId;
        return this;
    }

    public Cell setUnit(Unit unit) {
        this.unit = unit.getId();
        return this;
    }

    public Cell setOwner(int playerId) {
        this.owner = playerId;
        return this;
    }

    public Cell setOwner(Player player) {
        this.owner = player.getId();
        return this;
    }

    public Cell setScore(int score) {
        this.score = score;
        return this;
    }

    public Cell setX(int x) {
        this.x = x;
        return this;
    }

    public Cell setY(int y) {
        this.y = y;
        return this;
    }

    @Override
    public Cell clone() {
        return new Cell(x, y, land, building, unit, owner);
    }
}
