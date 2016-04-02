package app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args){
        
        io.Parser.updateGame("0:OK:M:[[[F,O,N,-1];[P,O,N,-1];[M,O,N,-1];[P,O,N,-1];[R,O,N,-1];];[[F,O,N,-1];[F,N,V,0];[M,O,N,-1];[P,O,N,-1];[R,O,N,-1];];[[F,O,N,-1];[P,P,N,0];[M,O,N,-1];[P,O,N,-1];[R,O,N,-1];];[[F,O,N,-1];[P,O,N,-1];[M,O,N,-1];[F,O,V,1];[R,O,N,-1];];[[F,O,N,-1];[P,O,N,-1];[M,O,N,-1];[P,O,N,-1];[R,O,N,-1];];]:U:P0:[[0,0,1,2,1];[1,2,4,1,1];];10:P1:[];110:", 0);
        
        Pattern cellPattern = Pattern.compile("\\[;.;.;.;(?:-?\\d+)\\];");
        Pattern rowPattern = Pattern.compile("\\[(?:"+cellPattern.pattern()+")+\\];");
        Pattern mapPattern = Pattern.compile("(?:"+rowPattern.pattern()+")+");
        Pattern mapCapturePattern = Pattern.compile("("+rowPattern.pattern()+")+");
        Pattern playerUnitsPattern = Pattern.compile("P\\d+:\\[(?:\\d+,\\d+,\\d+;)*\\];\\d+;");
        Pattern unitsPattern = Pattern.compile("(?:"+playerUnitsPattern.pattern()+")+");
        Pattern responsePattern = Pattern.compile("OK:P(\\d+) M\\[("+mapPattern.pattern()+")\\];U:("+unitsPattern.pattern()+")");
        
        String test = "OK:P0 M[[[;F;N;N;-1];[;F;N;N;-1];[;F;N;N;-1];[;F;N;N;-1];[;F;N;N;-1];];[[;P;N;N;-1];[;F;A;V;0];[;P;N;N;-1];[;P;N;N;-1];[;P;N;N;-1];];[[;M;N;N;-1];[;M;N;N;-1];[;M;N;N;-1];[;M;N;N;-1];[;M;N;N;-1];];[[;P;N;N;-1];[;P;N;N;-1];[;P;N;N;-1];[;F;N;V;1];[;P;N;N;-1];];[[;R;N;N;-1];[;R;N;N;-1];[;R;N;N;-1];[;R;N;N;-1];[;R;N;N;-1];];];U:P0:[0,2,1;];940;P1:[];1000;";
        
        
        Matcher responseMatcher = responsePattern.matcher(test);
        System.out.println(responseMatcher.matches());
        String playerResult = responseMatcher.group(1);
        String mapResult = responseMatcher.group(2);
        String playersResult = responseMatcher.group(3);
        
        System.out.println(playerResult);
        System.out.println(mapResult);
        System.out.println(playersResult);
        
        Matcher mapMatcher = mapCapturePattern.matcher(mapResult);
        System.out.println(mapMatcher.matches());
        System.out.println(mapMatcher.groupCount());
        /*for(int i = 0; i< mapMatcher.groupCount(); i++){
            System.out.println(mapMatcher.group(i));
        }*/
        
       

        
        
        
    }
}
