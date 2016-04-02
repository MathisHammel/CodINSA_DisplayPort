package models;

/**
 * Created by Charles on 02/04/2016.
 */
public class Cell {
    public Land land;
    public Building building;
    public Unit unit;
    public int owner;

    public Cell(Land land, Building building) {
        this(land, building, null);
    }

    public Cell(Land land, Building building, Unit unit) {
        this(land, building, unit, -1);
    }

    public Cell(Land land, Building building, Unit unit, int owner) {
        this.land = land;
        this.building = building;
        this.unit = unit;
        this.owner = owner;
    }
}
