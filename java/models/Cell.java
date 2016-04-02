package models;

/**
 * Created by Charles on 02/04/2016.
 */
public class Cell {
    public Land land;
    public Building building;
    public Unit unit;
    public int owner;
    public int coefficient;
    public int x;
    public int y;

    public Cell(int x, int y, Land land, Building building) { this(x, y, land, building, null); }

    public Cell(int x, int y, Land land, Building building, Unit unit) {
        this(x, y, land, building, unit, -1);
    }

    public Cell(int x, int y, Land land, Building building, Unit unit, int owner) {
        this.x = x;
        this.y = y;
        this.land = land;
        this.building = building;
        this.unit = unit;
        this.owner = owner;
    }

}
