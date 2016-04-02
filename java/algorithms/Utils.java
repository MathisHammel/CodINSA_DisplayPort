package algorithms;

/**
 * Created by loict on 02/04/2016.
 */
public class Utils {
    public static int infiniteDistance(int x1, int y1, int x2, int y2){
        return Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2));
    }
}
