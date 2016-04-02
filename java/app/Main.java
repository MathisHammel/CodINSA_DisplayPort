package app;

import algorithms.BehaviourBad;
import io.Action;
import io.Parser;
import io.SocketManager;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import models.Game;

public class Main {
    public static void main(String[] args) {
        //new SocketManager("127.0.0.1", 8080);
        
        Game g = Parser.parse("0:OK:M:[[[F,O,N,-1];[P,O,N,-1];[M,O,N,-1];[P,O,N,-1];[R,O,N,-1];];[[F,O,N,-1];[F,I,V,0];[M,O,N,-1];[P,O,N,-1];[R,O,N,-1];];[[F,O,N,-1];[P,O,N,-1];[M,O,N,-1];[P,O,N,-1];[R,O,N,-1];];[[F,O,N,-1];[P,O,N,-1];[M,O,N,-1];[F,O,V,1];[R,O,N,-1];];[[F,O,N,-1];[P,O,N,-1];[M,O,N,-1];[P,O,N,-1];[R,O,N,-1];];]:U:P0:[[0,2,4,1,1];];50:P1:[];100:");
    
        BehaviourBad b = new BehaviourBad();
        List<Action> decideActions = b.decideActions(g);
        
        for(Action a : decideActions) System.out.println(a.serialize());
    }
}
