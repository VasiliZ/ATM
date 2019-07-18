package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Home on 18.07.2019.
 */
public class Utils {
    public static boolean checkCreditCardNumber(String inputCardNumber){
        String stringPattern = "(\\d{4}[-]\\d{4}[-]\\d{4}[-]\\d{4})";
        Pattern pattern = Pattern.compile(stringPattern);
        Matcher matcher = pattern.matcher(inputCardNumber);
        return matcher.matches();

    }

    public static boolean checkPin(String pin) {
        String stringPattern = "\\d{4}";
        Pattern pattern = Pattern.compile(stringPattern);
        Matcher matcher = pattern.matcher(pin);
        return matcher.matches();
    }
}
