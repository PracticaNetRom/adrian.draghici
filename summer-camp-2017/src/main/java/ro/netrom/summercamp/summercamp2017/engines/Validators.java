package ro.netrom.summercamp.summercamp2017.engines;

import java.util.regex.Pattern;

public class Validators {
	
	public static boolean isAlphaOnly(String input, int length) {
        String regex = "[a-zA-Z]{3," + length + "}";
        return Pattern.matches(regex, input);
    }

    public static boolean isAlphaOnlyWithSpace(String input, int length) {
        String regex = "[a-zA-Z ]{3," + length + "}";
        return Pattern.matches(regex, input);
    }
    
    public static boolean isAlphaNumeric(String input, int length) {
        String regex = "[a-zA-Z0-9]{3," + length + "}";
        return Pattern.matches(regex, input);
    }
    
    public static boolean isAlphaNumericWithSpace(String input, int length) {
        String regex = "[a-zA-Z0-9 ]{3," + length + "}";
        return Pattern.matches(regex, input);
    }

    public static boolean isText(String input, int length) {
        String regex = "[a-zA-Z0-9 ~@*()_\\-+=,.?;:-^\\s!]{3," + length + "}";
        return Pattern.matches(regex, input);
    }
    
    public static boolean isEmailAddress(String email) {
        String regex = "[a-z0-9._%+-]+[@]{1}+[a-z0-9.-]+[.]{1}+[a-z]{2,6}";
        return Pattern.matches(regex, email);
    }

    public static boolean isPhoneNumber(String phoneNumber) {
        String regex = "[0-9]{10,12}";
        return Pattern.matches(regex, phoneNumber);
    }
    public static boolean isNumber(String number) {
        String regex = "[0-9]{1,4}";
        return Pattern.matches(regex, number);
    }
}
