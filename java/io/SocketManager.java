package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import algorithms.ArtificialIntelligence;
import java.util.ArrayDeque;
import java.util.Queue;
import models.Game;
import rules.Action;
import rules.actions.EndOfTurnAction;

public class SocketManager {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    
    private int playerNumber;
    
    private Queue<Action> actionsToDo = new ArrayDeque<>();
    
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
            this.playerNumber = Integer.parseInt(playerNumber.substring(6));
            
            Game.OUR_ID = this.playerNumber;
            
            writer.println("OK");
            writer.flush();
            
            System.out.println("Connected to server.");
        } catch (IOException ex) {
            System.err.println("FATAL: Could not connect to socket!! "+ex.getMessage());
            System.exit(1);
        }
        
        new Thread("Server Listener") {
            @Override public void run() {
                try {
                    String serverResponse;
                    while((serverResponse = reader.readLine()) != null) {
                        if(serverResponse.startsWith("M:")) {
                            System.out.println("Received initial situation.");
                            
                            serverResponse = "0:OK:"+ serverResponse;
                        }
                        
                        if(serverResponse.split(":")[1].equals("OK")) {
                            System.out.println("Received new map");
                            
                            Game game = Parser.parse(serverResponse);
                            
                            if(game.getCurrentPlayer() == game.getOurPlayer()) {
                                if(actionsToDo.isEmpty()) {
                                    List<Action> actions = ArtificialIntelligence.getNextActions(game);
                                    actionsToDo.addAll(actions);

                                    System.out.println("Got "+actionsToDo.size()+" new action(s)");
                                    
                                    if(actionsToDo.isEmpty()) {
                                        actionsToDo.add(new EndOfTurnAction());
                                        System.out.println("Added default end of turn action");
                                    }
                                }
                                
                                boolean repeat = true;
                                while(!actionsToDo.isEmpty() && repeat) {
                                    Action nextAction = actionsToDo.poll();
                                    if(nextAction.check(game)) { 
                                        send(nextAction);
                                        repeat = false;
                                    } else {
                                        System.out.println("[Action "+nextAction.serialize()+" skipped]");
                                    }

                                    System.out.println(actionsToDo.size()+" action(s) left");
                                    
                                    if(repeat && actionsToDo.isEmpty()) {
                                        actionsToDo.add(new EndOfTurnAction());
                                        System.out.println("Added default end of turn action");
                                    }
                                }
                            } else {
                                actionsToDo.clear();
                                
                                System.out.println("Other player playing...");
                            }                            
                        } else {
                            System.err.println("Server refused command, think I did something bad");
                            System.err.println(serverResponse);
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
    
    /*
    private void send(List<Action> actions) {
        for (Action action: actions) {
            send(action);
        }
    }
    */

    private void send(Action action) {
        String actionString = action.serialize();
        System.out.println("Sent: "+actionString);
        writer.println(actionString);
        writer.flush();
    }

}
