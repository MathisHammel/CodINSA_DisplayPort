package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import models.Cell;

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
                        if(s.startsWith("OK")) {
                            
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
}
