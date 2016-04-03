package algorithms;

import algorithms.Utils;
import models.*;
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
        double seed = Math.random();
        Map<Integer, Unit> builders = game.getCurrentPlayer().getUnits(UnitType.ENGINEER);
        List<Action> actions = new LinkedList<>();

        for(int id: builders.keySet()) {
            Unit curBuilder = builders.get(id);
            Cell curCell = game.getWorld().getCell(curBuilder.getX(), curBuilder.getY());
            if(curCell.getLand() == Land.RIVER && curCell.getBuilding() != Building.BRIDGE) {
                double proba = 1 - 150./(150.+game.getCurrentPlayer().getGold());
                double proba2 = 1 - 250./(250.+game.getCurrentPlayer().getGold());
                if(Utils.random(seed, curBuilder, proba) && Rules.checkBuild(game, curBuilder.getId(), Building.BRIDGE)) {
                    actions.add(new BuildAction(curBuilder, Building.BRIDGE));
                    game = Simulator.simulateBuild(game, curBuilder.getId(), Building.BRIDGE);
                } else if(Utils.random(seed, curBuilder, proba) && Rules.checkBuild(game, curBuilder.getId(), Building.ROAD)) {
                    actions.add(new BuildAction(curBuilder, Building.ROAD));
                    game = Simulator.simulateBuild(game, curBuilder.getId(), Building.ROAD);
                } else if (Utils.random(seed, curBuilder, proba2)) {
                    Building building;
                    if (seed < 0.4) {
                        building = Building.FORT;
                    } else if(seed < 0.8){
                        building = Building.ROAD;
                    } else {
                        building = Building.HOSPITAL;
                    }
                    if(Rules.checkBuild(game, curBuilder.getId(), building)) {
                        actions.add(new BuildAction(curBuilder, building));
                        game = Simulator.simulateBuild(game, curBuilder.getId(), building);
                    }
                }
            }
        }

        return actions;
    }
}
