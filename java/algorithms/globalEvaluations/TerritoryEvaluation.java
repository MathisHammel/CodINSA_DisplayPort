package algorithms.globalEvaluations;

import algorithms.globalEvaluations.GlobalEvaluationInterface;
import models.Building;
import models.Cell;
import models.Game;
import models.Land;

public class TerritoryEvaluation implements GlobalEvaluationInterface {
    @Override
    public double evaluate(Game game) {
        Cell[][] map = game.getWorld().getMap();

        int size = map.length;

        MapStats ms = new MapStats();
        MapStats[] playerMs = new MapStats[]{new MapStats(), new MapStats()};

        for(int x = 0; x < size; x++) {
            for(int y = 0; y < size; y++) {
                Cell curCell = map[x][y];
                int ownerId = curCell.getOwnerId();
                ms.cells++;
                if (curCell.getLand() == Land.RIVER) {
                    ms.water++;
                    if (curCell.getBuilding() == Building.BRIDGE) {
                        ms.bridges++;
                    }
                } else if(curCell.getBuilding() == Building.ROAD) {
                    ms.roads++;
                } else if(curCell.getBuilding() == Building.FORT) {
                    ms.forts++;
                }

                int curPonderation = curCell.getScore();
                ms.ponderatedCells += curPonderation;

                if(ownerId >= 0) {
                    playerMs[ownerId].cells++;
                    if (curCell.getLand() == Land.RIVER) {
                        playerMs[ownerId].water++;
                        if (curCell.getBuilding() == Building.BRIDGE) {
                            playerMs[ownerId].bridges++;
                        }
                    } else if(curCell.getBuilding() == Building.ROAD) {
                        playerMs[ownerId].roads++;
                    } else if(curCell.getBuilding() == Building.FORT) {
                        playerMs[ownerId].forts++;
                    }
                    playerMs[ownerId].ponderatedCells += curPonderation;
                }
            }
        }

        MapStats curPlayer = playerMs[game.getCurrentPlayerId()];
        MapStats otherPlayer = playerMs[game.getOtherPlayerId()];


        double curRatio = curPlayer.cells / (double) ms.cells;
        double otherRatio = otherPlayer.cells / (double) ms.cells;
        double freeRatio = 1 - curRatio - otherRatio;

        double territoryDelta;
        territoryDelta = otherPlayer.cells - curPlayer.cells;
        if (freeRatio < 0.3) {
            territoryDelta *= 2;
        }

        double waterRatio = ms.water / ms.cells;
        double bridgesRatio = ms.bridges / ms.water;
        double waterDelta = ms.bridges-ms.water;
        if (bridgesRatio < 0.25) {
            waterDelta *= 1.5;
        }
        if (waterRatio > 0.4) {
            waterDelta *= 2;
        }

        return 20 * curPlayer.cells + 30 * territoryDelta + 10 * waterDelta + Math.sqrt(curPlayer.forts);
    }

    public class MapStats {
        int cells;
        int water;
        int bridges;
        int roads;
        int forts;
        int ponderatedCells;
        MapStats() {
            cells = 0;
            water = 0;
            bridges = 0;
            roads = 0;
            forts = 0;
            ponderatedCells = 0;
        }
    }
}
