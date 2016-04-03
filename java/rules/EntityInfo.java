package rules;

import models.Building;

public class EntityInfo {
    public static int getBuildingCost(Building building) {
        switch(building) {
            case BRIDGE:
                return 50;
            case FORT:
                return 75;
            case HOSPITAL:
                return 60;
            case ROAD:
                return 35;
            case NONE:
                System.err.println("How am I supposed to build nothing?");
                return 10000;
            case CITY:
                System.err.println("Can't build a city!");
                return 10000;
        }
        System.err.println("Unknown building");
        return 10000;
    }
    public static char getBuildingCode(Building building) {
        switch(building) {
            case BRIDGE:
                return 'P';
            case FORT:
                return 'F';
            case HOSPITAL:
                return 'H';
            case ROAD:
                return 'R';
            case NONE:
                return 'N';
            case CITY:
                return 'V';
        }
        return 'N';
    }
}
