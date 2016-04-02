package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import models.Building;
import models.Cell;
import models.Game;
import models.Unit;
import models.UnitType;

public class SocketManager {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    
    private int playNumber;
    
    /**
     * Se connecte à un serveur.
     * @param ip Adresse IP du serveur
     * @param port Port
     */
    public SocketManager(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
            
            String playerNumber = reader.readLine();
            playNumber = Integer.parseInt(playerNumber.substring(6));
            
            Game.OUR_ID = playNumber;
            
            writer.println("OK");
            
            System.out.println("Connected to server.");
        } catch (IOException ex) {
            System.err.println("FATAL: Could not connect to socket!! "+ex.getMessage());
            System.exit(1);
        }
        
        new Thread("Server Listener") {
            @Override public void run() {
                try {
                    String s;
                    while((s = reader.readLine()) != null) {
                        if(s.split(":")[1].equals("OK")) {
                            Parser.updateGame(s, playNumber);
                            //TODO faire quelque chose de ce Game
                        } else {
                            System.err.println("Server refused command, think I did something bad");
                            System.err.println(s);
                        }
                    }
                } catch (IOException ex) {
                    System.err.println("FATAL: Socket read error! "+ex.getMessage());
                    System.exit(1);
                }
            }
        }.start();
    }
    
    /**
     * Interrompt la connexion
     * @throws IOException En cas d'erreur d'entrée sortie
     */
    public void disconnect() throws IOException {
        reader.close();
        writer.close();
        socket.close();
    }
    
    /**
     * Attaque un autre personnage.
     * @param unit Unité qui doit attaquer
     * @param x Coordonnée x à attaquer
     * @param y Coordonnée y à attaquer
     */
    public void attack(Unit unit, int x, int y) {
        send("A,"+unit.getId()+","+x+","+y);
    }
    
    /**
     * Demande à une unité de fabriquer un bâtiment.
     * @param unit Unité qui doit fabriquer
     * @param building Bâtiment à fabriquer
     */
    public void build(Unit unit, Building building) {
        char buildId = 'Q';
        switch(building) {
            case BRIDGE: buildId = 'P'; break;
            case FORT: buildId = 'F'; break;
            case HOSPITAL: buildId = 'H'; break;
            case ROAD: buildId = 'R'; break;
            case NONE: 
                System.err.println("How am I supposed to build nothing?");
                return;
            case CITY:
                System.err.println("Can't build a city!");
                return;
        }
        
        send("B,"+unit.getId()+","+buildId);
    }
    
    public void move(Unit unit, int x, int y) {
        send("M,"+unit.getId()+","+x+","+y);
    }
    
    /**
     * Creates a new unit at the user's home city.
     * @param type Unit type to create
     */
    public void create(UnitType type) {
        char createId = 'Q';
        if(type == UnitType.ARCHER) createId = 'A';
        else if(type == UnitType.BALISTA) createId = 'B';
        else if(type == UnitType.DWARF) createId = 'N';
        else if(type == UnitType.ENGINEER) createId = 'I';
        else if(type == UnitType.PALADIN) createId = 'C';
        else if(type == UnitType.PEASANT) createId = 'P';
        else if(type == UnitType.SCOUT) createId = 'E';
        else if(type == UnitType.SOLDIER) createId = 'S';
        else {
            System.err.println("This UnitType is unknown!");
            return;
        }
        
        send("C,"+createId);
    }
    
    /**
     * Orders a unit to destroy the building on which it is currently.
     * @param unit The unit
     */
    public void destroy(Unit unit) {
        send("D,"+unit.getId());
    }
    
    private void send(String s) {
        System.out.println("Sent: "+s);
        writer.println(s);
    }
}
