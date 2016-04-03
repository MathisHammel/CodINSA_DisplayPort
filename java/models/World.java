package models;

import algorithms.CellScoreOnCitiesDistance;

public class World extends GameEntity {
    protected int size;
    protected Cell[][] map;

    public World(Cell[][] map) {
        super(null);
        this.size = map.length;
        this.map = map;
    }

    public World clone() {
        Cell[][] map = new Cell[this.size][this.size];
        for(int x = 0; x < this.size; x++) {
            for(int y = 0; y < this.size; y++) {
                map[x][y] = this.map[x][y].clone();
            }
        }
        return new World(map);
    }

    public void bindGame(Game g) {
        this.setGame(g);
        for(int x = 0; x < this.size; x++) {
            for(int y = 0; y < this.size; y++) {
                this.map[x][y].bindGame(g);
            }
        }
    }

    public Cell[][] getMap() {
        return this.map;
    }

    public int getSize() {
        return this.size;
    }

    public World setMap(Cell[][] map) {
        this.map = map;
        if(this.game != null) {
            this.bindGame(this.getGame());
        }
        return this;
    }

    /**
     * Returns the cell at (x, y) or null if the cell does not exists
     * @param x
     * @param y
     * @return
     */
    public Cell getCell(int x, int y) {
        if(x < 0 || x >= this.size || y < 0 || y >= this.size) {
            return null;
        }
        return this.map[x][y];
    }

    public World setCell(int x, int y, Cell cell) {
        if(x < 0 || x >= this.size || y < 0 || y >= this.size) {
            System.err.println("Trying to set out of bounds cell at " + x + ";" + y);
        } else {
            this.map[x][y] = cell;
            if(this.game != null){
                cell.bindGame(this.getGame());
            }
        }
        return this;
    }
    
    /**
     * Calcule les scores de chaque case du plateau.
     */
    public void calculateScores() {
        for(int x = 0; x < this.size; x++) {
            for(int y = 0; y < this.size; y++) {
                this.map[x][y].setScore(new CellScoreOnCitiesDistance().evaluate(game, this.map[x][y]));
            }
        }
    }

}
