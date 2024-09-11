package com.x.loan.domain.validator;

public class APValidator {

    public static boolean isValidAP(String ap) {
        if (ap == null || ap.length() != 10) {
            return false;
        }

        char lastDigit = ap.charAt(9);

        String firstNineDigits = ap.substring(0, 9);
        if (firstNineDigits.indexOf(lastDigit) != -1) {
            return false;
        }

        return true;
    }

}


