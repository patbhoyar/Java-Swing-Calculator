/**
 * Created by admin on October/9/14.
 */
public class myUtil {

    public static boolean isNumeric(String str){
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}
