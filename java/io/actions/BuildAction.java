package io.actions;

import io.Action;
import models.Building;
import models.Game;
import models.Unit;


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
        char buildId = 'Q';
        switch(this.building) {
            case BRIDGE: buildId = 'P'; break;
            case FORT: buildId = 'F'; break;
            case HOSPITAL: buildId = 'H'; break;
            case ROAD: buildId = 'R'; break;
            case NONE:
                System.err.println("How am I supposed to build nothing?");
                return "";
            case CITY:
                System.err.println("Can't build a city!");
                return "";
        }
        return "B,"+this.builderId+","+buildId;
    }

    @Override
    public boolean check(Game game) {
        return false;
    }
}
