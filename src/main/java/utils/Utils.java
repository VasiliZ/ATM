package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static boolean checkCreditCardNumber(String inputCardNumber) {
        String stringPattern = "(\\d{4}[-]\\d{4}[-]\\d{4}[-]\\d{4})";
        return verifyInput(inputCardNumber, stringPattern);

    }

    public static boolean checkPin(String pin) {
        String stringPattern = "\\d{4}";
        return verifyInput(pin, stringPattern);
    }

    public static boolean checkBackSymbol(String backSymbol) {
        String pattern = "[<]";
        return verifyInput(backSymbol, pattern);
    }


    private static boolean verifyInput(String inputData, String stringPattern) {
        Pattern pattern = Pattern.compile(stringPattern);
        Matcher matcher = pattern.matcher(inputData);
        return matcher.matches();
    }
}
