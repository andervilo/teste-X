package com.x.loan.domain.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class APValidatorTest {

    @Test
    void isValidAPWithValidAP() {
        assertTrue(APValidator.isValidAP("1234567890"));
    }

    @Test
    void isValidAPWithNullAP() {
        assertFalse(APValidator.isValidAP(null));
    }

    @Test
    void isValidAPWithIncorrectLength() {
        assertFalse(APValidator.isValidAP("123456789"));
    }

    @Test
    void isValidAPWithLastDigitInFirstNine() {
        assertFalse(APValidator.isValidAP("1234567891"));
    }

    @Test
    void isValidAPWithEmptyString() {
        assertFalse(APValidator.isValidAP(""));
    }
}