package com.x.loan.domain.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CPFValidatorTest {

    @Test
    void isValidCPFWithValidCPF() {
        assertTrue(CPFValidator.isValidCPF("123.456.789-09"));
    }

    @Test
    void isValidCPFWithNullCPF() {
        assertFalse(CPFValidator.isValidCPF(null));
    }

    @Test
    void isValidCPFWithIncorrectLength() {
        assertFalse(CPFValidator.isValidCPF("123.456.789"));
    }

    @Test
    void isValidCPFWithAllSameDigits() {
        assertFalse(CPFValidator.isValidCPF("11111111111"));
    }

    @Test
    void isValidCPFWithInvalidDigits() {
        assertFalse(CPFValidator.isValidCPF("123.456.789-00"));
    }

    @Test
    void isValidCPFWithEmptyString() {
        assertFalse(CPFValidator.isValidCPF(""));
    }
}