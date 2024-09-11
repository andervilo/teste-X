package com.x.loan.domain.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CNPJValidatorTest {

    @Test
    void isValidCNPJWithValidCNPJ() {
        assertTrue(CNPJValidator.isValidCNPJ("11.222.333/0001-81"));
    }

    @Test
    void isValidCNPJWithNullCNPJ() {
        assertFalse(CNPJValidator.isValidCNPJ(null));
    }

    @Test
    void isValidCNPJWithIncorrectLength() {
        assertFalse(CNPJValidator.isValidCNPJ("11.222.333/0001"));
    }

    @Test
    void isValidCNPJWithAllSameDigits() {
        assertFalse(CNPJValidator.isValidCNPJ("11111111111111"));
    }

    @Test
    void isValidCNPJWithInvalidDigits() {
        assertFalse(CNPJValidator.isValidCNPJ("11.222.333/0001-82"));
    }

    @Test
    void isValidCNPJWithEmptyString() {
        assertFalse(CNPJValidator.isValidCNPJ(""));
    }
}