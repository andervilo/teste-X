package com.x.loan.domain.validator;

import java.util.Objects;

public class EUValidator {

    public static boolean isValidEU(String eu) {
        if (Objects.isNull(eu)) {
            return false;
        }
        eu = eu.replaceAll("\\D", "");

        if (eu.length() != 8) {
            return false;
        }

        if (eu.matches("(\\d)\\1{7}")) {
            return false;
        }


        return verificaSomaIguala9(eu);
    }

    private static Boolean verificaSomaIguala9(String input) {
        char firstChar = input.charAt(0);
        char lastChar = input.charAt(input.length() - 1);

        int firstDigit = Character.getNumericValue(firstChar);
        int lastDigit = Character.getNumericValue(lastChar);

        return (firstDigit + lastDigit) == 9;
    }

}

