package com.x.loan.domain.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EUValidatorTest {

    @Test
    void isValidEUWithValidEU() {
        assertTrue(EUValidator.isValidEU("12345678"));
    }

    @Test
    void isValidEUWithNullEU() {
        assertFalse(EUValidator.isValidEU(null));
    }

    @Test
    void isValidEUWithIncorrectLength() {
        assertFalse(EUValidator.isValidEU("1234567"));
    }

    @Test
    void isValidEUWithAllSameDigits() {
        assertFalse(EUValidator.isValidEU("11111111"));
    }

    @Test
    void isValidEUWithSumNotEqualToNine() {
        assertFalse(EUValidator.isValidEU("12345670"));
    }

    @Test
    void isValidEUWithEmptyString() {
        assertFalse(EUValidator.isValidEU(""));
    }
}