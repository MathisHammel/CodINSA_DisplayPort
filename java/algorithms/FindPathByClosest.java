package algorithms;

import models.Cell;
import models.Game;
import models.Unit;
import models.UnitType;

import java.util.*;


public class FindPathByClosest implements FindPathInterface {
    @Override
    public List<String> evaluatePath(Game game, Unit unitToMove, Cell destination) {
        LinkedList<String> operations = new LinkedList<>();
        Map<Cell, Unit.ReachableResult> cells = unitToMove.getReachableCells(game.getWorld());

        // selectionner la case la plus proche
        Cell closestCell = null;
        int minDistance = game.getWorld().getSize()+1;
        for (Cell c: cells.keySet()) {
            int distance = Utils.infiniteDistance(c.getX(),c.getY(),destination.getX(), destination.getY());
            if(distance < minDistance){
                minDistance = distance;
                closestCell = c;
            }
        }
        if(closestCell != null)
        {
            // il faut reconstituer le chemin
            while(closestCell != game.getWorld().getCell(unitToMove.getX(), unitToMove.getY())){
                operations.addFirst("Bouger bouger l'ingénieur en "+closestCell.getX()+";"+closestCell.getY());
                closestCell = cells.get(closestCell).from;
            }
        }
        return operations;
    }
}
