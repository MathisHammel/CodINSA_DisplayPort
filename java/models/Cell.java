package models;

/**
 * Created by Charles on 02/04/2016.
 */
public class Cell extends GameEntity {
    private Land land;
    private Building building;
    private int unit;
    private int owner;
    private int score;
    private int x;
    private int y;

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

    public Land getLand() {
        return this.land;
    }

    public Building getBuilding() {
        return this.building;
    }

    public Unit getUnit() {
        return null;
    }

    public Player getOwner() {
        return null;
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
