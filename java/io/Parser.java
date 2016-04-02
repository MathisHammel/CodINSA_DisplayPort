
package io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.Building;
import models.Cell;
import models.Game;
import models.Land;
import models.Player;
import models.Unit;
import models.units.Archer;
import models.units.Balista;
import models.units.Dwarf;
import models.units.Engineer;
import models.units.Paladin;
import models.units.Peasant;
import models.units.Scout;
import models.units.Soldier;

public class Parser {
    
    /**
     * Permet de traiter la chaîne de caractères donnée pour créer un nouvel état.
     * @param s Chaîne à traiter
     * @return Nouveau jeu
     */
    public static Game parse(String s) {
        s = s.replace("[", "").replace("]", "");
        
        String[] entries = s.split(":");
        
        int currentNumber = Integer.parseInt(entries[0]);

        String[] mapRows = entries[3].split(";;");

        Cell[][] cells = new Cell[mapRows.length][mapRows.length];
        
        Unit[][] units = new Unit[mapRows.length][mapRows.length];

        for(int i = 0; i < mapRows.length; i++) {
            String[] mapCells = mapRows[i].split(";");

            for(int j = 0; j < mapCells.length; j++) {
                String[] mapInformation = mapCells[j].split(",");
                
                units[i][j] = getUnit(mapInformation[1], i, j);
                
                cells[i][j] = new Cell(i, j, getLand(mapInformation[0]), 
                        getBuilding(mapInformation[2]), -1, 
                        Integer.parseInt(mapInformation[3]));
            }
        }
        
        List<Player> players = new ArrayList<>();
        
        for(int i = 5; i < entries.length; i += 2) {
            Map<Integer, Unit> playerUnits = new HashMap<>();
            
            int playNumber = Integer.parseInt(entries[i].substring(1));
            
            String[] playerInfos = entries[i+1].split(";");
            
            for(int j = 0; j < playerInfos.length - 2; j++) {
                String[] playerInfo = playerInfos[j].split(",");
                
                int x = Integer.parseInt(playerInfo[3]);
                int y = Integer.parseInt(playerInfo[4]);
                units[x][y].setId(Integer.parseInt(playerInfo[0]));
                units[x][y].setActions(Integer.parseInt(playerInfo[1]));
                units[x][y].setHealth(Integer.parseInt(playerInfo[2]));
                
                playerUnits.put(units[x][y].getId(), units[x][y]);
                cells[x][y].setUnit(units[x][y]);
            }
            
            int gold = Integer.parseInt(playerInfos[playerInfos.length - 1]);
            
            Player p = new Player(playNumber, gold, playerUnits);
            players.add(p);
        }
        

        return new Game(players.get(0), players.get(1), currentNumber, cells);
    }
    
    
    private static Land getLand(String s) {
        switch(s.charAt(0)) {
            case 'P': return Land.PLAIN;
            case 'F': return Land.FOREST;
            case 'R': return Land.RIVER;
            case 'M': return Land.MONTAIN;
        }
        
        return null;
    }
    
    private static Building getBuilding(String s) {
        switch(s.charAt(0)) {
            case 'N': return Building.NONE;
            case 'V': return Building.CITY;
            case 'F': return Building.FORT;
            case 'R': return Building.ROAD;
            case 'P': return Building.BRIDGE;
            case 'H': return Building.HOSPITAL;
        }
        
        return null;
    }
    
    private static Unit getUnit(String s, int x, int y) {
        switch(s) {
            case "P": return new Peasant(x, y);
            case "A": return new Archer(x, y);
            case "N": return new Dwarf(x, y);
            case "O": return null;
            case "B": return new Balista(x, y);
            case "I": return new Engineer(x, y);
            case "E": return new Scout(x, y);
            case "C": return new Paladin(x, y);
            case "S": return new Soldier(x, y);
        }
        
        return null;
    }
}
