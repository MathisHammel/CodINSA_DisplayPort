package models;

/**
 * Created by Charles on 02/04/2016.
 */
public class Cell {
    public Land land;
    public Building building;
    public int unit;
    public int owner;
    public int score;
    public int x;
    public int y;

    public Cell(int x, int y, Land land, Building building) { this(x, y, land, building, -1); }

    public Cell(int x, int y, Land land, Building building, int unit) {
        this(x, y, land, building, unit, -1);
    }

    public Cell(int x, int y, Land land, Building building, int unit, int owner) {
        this.x = x;
        this.y = y;
        this.land = land;
        this.building = building;
        this.unit = unit;
        this.owner = owner;
    }

    public Cell clone() {
        return new Cell(x, y, land, building, unit, owner);
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
}
