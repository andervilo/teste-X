package com.x.loan.domain.validator;

import java.util.Objects;

public class CPFValidator {

    public static boolean isValidCPF(String cpf) {

        if (Objects.isNull(cpf)) {
            return false;
        }
        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int digito1 = calcularDigito(cpf.substring(0, 9));
        int digito2 = calcularDigito(cpf.substring(0, 9) + digito1);

        return cpf.equals(cpf.substring(0, 9) + digito1 + digito2);
    }

    private static int calcularDigito(String str) {
        int soma = 0;
        for (int i = 0; i < str.length(); i++) {
            soma += Character.getNumericValue(str.charAt(i)) * ((str.length() + 1) - i);
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }
}

