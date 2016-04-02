package algorithms;

import models.Cell;
import models.Game;
import models.Unit;
import models.UnitType;

import java.util.List;


public class FindPathByClosest implements FindPathInterface {
    @Override
    public List<String> evaluatePath(Game game, Unit unitToMove, Cell destination) {
        UnitType unitType = unitToMove.getUnitType();
        int actions = unitToMove.getActions();
        Cell unitCell = game.world.getCell(unitToMove.getX(), unitToMove.getY());

        // find closest cell reachable from
        return null;
    }
}
