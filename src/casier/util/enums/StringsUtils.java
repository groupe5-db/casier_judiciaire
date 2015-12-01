package casier.util.enums;

/**
 * Created by dassi on 01/12/15.
 */
public class StringsUtils {

    public static boolean checkValidString(String str){
        if(str.isEmpty() || str.equals("") || str == null || str.length() == 0){
            return false;
        }
        return true;
    }
}
