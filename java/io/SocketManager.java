package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import algorithms.ArtificialIntelligence;
import models.Game;

public class SocketManager {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    
    private int playerNumber;
    
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
                        if(serverResponse.split(":")[1].equals("OK")) {
                            Game game = Parser.parse(serverResponse);
                            if(false /*notre tour*/) {
                                List<Action> actions = ArtificialIntelligence.getNextActions(game);
                                // this.send();
                            }

                            // TODO: envoyer les actions
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
    
    private void send(List<Action> actions) {
        for (Action action: actions) {
            send(action);
        }
    }

    private void send(Action action) {
        String actionString = action.serialize();
        System.out.println("Sent: "+actionString);
        writer.println(actionString);
    }

}
