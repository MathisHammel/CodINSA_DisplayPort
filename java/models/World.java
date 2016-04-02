package models;

/**
 * Created by Charles on 02/04/2016.
 */
public class World {
    private int width;
    private int height;
    private Cell[][] map;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.initEmptyMap();
    }

    public World(int width, int height, Cell[][] map) {
        this.width = width;
        this.height = height;
        this.map = map;
    }

    public void initEmptyMap() {
        this.map = new Cell[this.width][this.height];
        for(int x = 0; x < this.width; x++) {
            for(int y = 0; y < this.height; y++) {
                this.map[x][y] = new Cell(Land.PLAINE, Building.NONE);
            }
        }
    }

    public Cell[][] getMap() {
        return this.map;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public World setMap(Cell[][] map) {
        this.map = map;
        return this;
    }

    /**
     * Returns the cell at (x, y) or null if the cell does not exists
     * @param x
     * @param y
     * @return
     */
    public Cell getCell(int x, int y) {
        if(x < 0 || x >= this.width || y < 0 || y >= this.height) {
            return null;
        }
        return this.map[x][y];
    }

    public World setCell(int x, int y, Cell cell) {
        if(x < 0 || x >= this.width || y < 0 || y >= this.height) {
            System.err.println("Trying to set out of bounds cell at " + x + ";" + y);
        } else {
            this.map[x][y] = cell;
        }
        return this;
    }
}
