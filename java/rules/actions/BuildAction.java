package rules.actions;

import rules.Action;
import models.Building;
import models.Game;
import models.Unit;
import rules.Rules;
import rules.EntityInfo;

/**
 * Demande à une unité de fabriquer un bâtiment.
 * builderId Unité qui doit fabriquer
 * building Bâtiment à fabriquer
 */
public class BuildAction implements Action {
    int builderId;
    Building building;

    public BuildAction(Unit builder, Building building) {
        this.builderId = builder.getId();
        this.building = building;
    }

    public BuildAction(int builderId, Building building) {
        this.builderId = builderId;
        this.building = building;
    }

    public String serialize() {
        return "B," + this.builderId + "," + EntityInfo.getBuildingCost(this.building);
    }

    @Override
    public boolean check(Game game) {
        return Rules.checkBuild(game, builderId, building);
    }
}
