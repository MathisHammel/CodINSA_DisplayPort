package io.actions;

import com.sun.org.apache.xml.internal.security.algorithms.JCEMapper;
import io.Action;
import models.*;
import algorithms.Rules;

/**
 * Demande à une unité de fabriquer un bâtiment.
 * builderId Unité qui doit fabriquer
 * building Bâtiment à fabriquer
 */

/**
 * Creates a new unit at the user's home city.
 */

public class CreateAction implements Action {
    UnitType unitType;

    public CreateAction(UnitType unitType) {
        this.unitType = unitType;
    }

    public String serialize() {
        char createId = 'Q';
        if(this.unitType == UnitType.ARCHER) createId = 'A';
        else if(this.unitType == UnitType.BALISTA) createId = 'B';
        else if(this.unitType == UnitType.DWARF) createId = 'N';
        else if(this.unitType == UnitType.ENGINEER) createId = 'I';
        else if(this.unitType == UnitType.PALADIN) createId = 'C';
        else if(this.unitType == UnitType.PEASANT) createId = 'P';
        else if(this.unitType == UnitType.SCOUT) createId = 'E';
        else if(this.unitType == UnitType.SOLDIER) createId = 'S';
        else {
            System.err.println("This UnitType is unknown!");
            return "";
        }

        return "C,"+createId;
    }

    @Override
    public boolean check(Game game) {
        return Rules.checkCreate(game, this.unitType);
    }
}
