package app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args){
        
        Pattern cellPattern = Pattern.compile("\\[,.,.,.,-?\\d+\\];");
        Pattern cellPatternCapture = Pattern.compile("\\[,(.),(.),(.),(-?\\d+)\\];");

        Pattern rowPattern = Pattern.compile("\\[(?:"+cellPattern.pattern()+")+\\];");

        Pattern mapPattern = Pattern.compile("(?:"+rowPattern.pattern()+")+");

        Pattern unitPattern = Pattern.compile("\\d+,\\d+,\\d+,\\d+,\\d+;");
        Pattern unitPatternCapture = Pattern.compile("(\\d+),(\\d+),(\\d+),(\\d+),(\\d+);");

        Pattern playerPattern = Pattern.compile("P\\d+:\\[(?:"+unitPattern.pattern()+")*\\];\\d+:");
        Pattern playerPatternCapture = Pattern.compile("P\\d+:\\[("+unitPattern.pattern()+")*\\];(\\d+):");

        Pattern playersPattern = Pattern.compile("(?:"+playerPattern.pattern()+")+");

        Pattern responsePattern = Pattern.compile("(\\d+):OK:M:\\[("+mapPattern.pattern()+")\\]:U:("+playersPattern.pattern()+")");
        
        String test = "1:OK:M:[[[,F,N,N,-1];[,F,N,N,-1];[,F,N,N,-1];[,F,N,N,-1];[,F,N,N,-1];];[[,P,N,N,-1];[,F,A,V,0];[,P,N,N,-1];[,P,N,N,-1];[,P,N,N,-1];];[[,M,N,N,-1];[,M,N,N,-1];[,M,N,N,-1];[,M,N,N,-1];[,M,N,N,-1];];[[,P,N,N,-1];[,P,N,N,-1];[,P,N,N,-1];[,F,N,V,1];[,P,N,N,-1];];[[,R,N,N,-1];[,R,N,N,-1];[,R,N,N,-1];[,R,N,N,-1];[,R,N,N,-1];];]:U:P0:[0,2,1,1,2;];940:P1:[];1000:";

        System.out.println(responsePattern);
        System.out.println(test);

        Matcher responseMatcher = responsePattern.matcher(test);
        if(!responseMatcher.matches()){
            System.err.println("Cannot match response");
        }
        String nextPlayerResult = responseMatcher.group(1);
        String mapResult = responseMatcher.group(2);
        String playersResult = responseMatcher.group(3);
        
        Matcher rowMatcher = rowPattern.matcher(mapResult);
        while(rowMatcher.find()) {
            String rowResult = rowMatcher.group();
            Matcher cellMatcher = cellPattern.matcher(rowResult);
            while(cellMatcher.find()) {
                String cellResult = cellMatcher.group();
                Matcher cellCapture = cellPatternCapture.matcher(cellResult);
                if(!cellCapture.matches()){
                    System.err.println("Cannot match cell");
                }
                String cellLand = cellCapture.group(1);
                String cellUnit = cellCapture.group(2);
                String cellBuilding = cellCapture.group(3);
                String cellOwner = cellCapture.group(4);
                System.out.println("("+cellLand+","+cellUnit+","+cellBuilding+","+cellOwner+")");
            }
        }

        Matcher playerMatcher = playerPattern.matcher(playersResult);
        while(playerMatcher.find()) {
            String playerResult = playerMatcher.group();
            Matcher playerCapture = playerPatternCapture.matcher(playerResult);
            if(!playerCapture.matches()){
                System.err.println("Cannot match player");
            }
            String unitsResult = playerCapture.group(1);
            String goldResult = playerCapture.group(2);
            System.out.println(goldResult);
            if(unitsResult != null) {
                Matcher unitMatcher = unitPattern.matcher(unitsResult);
                while(unitMatcher.find()) {
                    String unit = unitMatcher.group();
                    Matcher unitCapture = unitPatternCapture.matcher(unit);
                    if(!unitCapture.matches()){
                        System.err.println("Cannot match unit");
                    }
                    String unitId = unitCapture.group(1);
                    String unitX = unitCapture.group(2);
                    String unitY = unitCapture.group(3);
                    String unitHealth = unitCapture.group(4);
                    String unitActions = unitCapture.group(5);
                }
            }
        }
    }
}
