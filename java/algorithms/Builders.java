package algorithms;

import algorithms.Utils;
import models.*;
import models.units.Engineer;
import rules.Action;
import rules.Rules;
import rules.Simulator;
import rules.UnitType;
import rules.actions.BuildAction;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Builders {
    public static List<Action> predictBuilds(Game game) {
        Map<Integer, Unit> builders = game.getCurrentPlayer().getUnits(UnitType.ENGINEER);
        List<Action> actions = new LinkedList<>();

        for(int id: builders.keySet()) {
            Unit curBuilder = builders.get(id);
            Action todo = predictBuilds(game, curBuilder);
            if(todo != null)
                actions.add(todo);
        }
        return actions;
    }

    public static Action predictBuilds(Game game, Unit engineer){
        double seed = Math.random();
        Cell curCell = game.getWorld().getCell(engineer.getX(), engineer.getY());
        double proba = 1 - 150./(150.+game.getCurrentPlayer().getGold());
        double proba2 = 1 - 250./(250.+game.getCurrentPlayer().getGold());
        if(curCell.getLand() == Land.RIVER && curCell.getBuilding() != Building.BRIDGE) {
            if (Utils.random(seed, engineer, proba) && Rules.checkBuild(game, engineer.getId(), Building.BRIDGE)) {
                return new BuildAction(engineer, Building.BRIDGE);
                // game = Simulator.simulateBuild(game, engineer.getId(), Building.BRIDGE);
            }
        }else if(Utils.random(seed, engineer, proba/10) && Rules.checkBuild(game, engineer.getId(), Building.ROAD)) {
                return new BuildAction(engineer, Building.ROAD);
                // game = Simulator.simulateBuild(game, engineer.getId(), Building.ROAD);
            } else if (Utils.random(seed, engineer, proba2)) {
                Building building;
                if (seed < 0.4) {
                    building = Building.FORT;
                } else if(seed < 0.8){
                    building = Building.ROAD;
                } else {
                    building = Building.HOSPITAL;
                }
                if(Rules.checkBuild(game, engineer.getId(), building)) {
                    return new BuildAction(engineer, building);
                    // game = Simulator.simulateBuild(game, engineer.getId(), building);
                }
            }
        return null;
    }
}
