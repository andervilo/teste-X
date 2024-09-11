package com.x.loan.domain.factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


import com.x.loan.domain.enumeration.IdentificadorType;
import com.x.loan.domain.exception.CustomException;
import com.x.loan.domain.vo.*;

class IdentificadorFactoryTest {

    @Test
    void createPessoaFisica() {
        IdentificadorBaseVO result = IdentificadorFactory.create(IdentificadorType.PF, "72316057020");
        assertNotNull(result);
        assertEquals(IdentificadorType.PF, result.getTipo());
        assertEquals("72316057020", result.getIdentificador());
    }

    @Test
    void createPessoaJuridica() {
        IdentificadorBaseVO result = IdentificadorFactory.create(IdentificadorType.PJ, "72387599000179");
        assertNotNull(result);
        assertEquals(IdentificadorType.PJ, result.getTipo());
        assertEquals("72387599000179", result.getIdentificador());
    }

    @Test
    void createEstudanteUniversitario() {
        IdentificadorBaseVO result = IdentificadorFactory.create(IdentificadorType.EU, "12345678");
        assertNotNull(result);
        assertEquals(IdentificadorType.EU, result.getTipo());
        assertEquals("12345678", result.getIdentificador());
    }

    @Test
    void createAposentado() {
        IdentificadorBaseVO result = IdentificadorFactory.create(IdentificadorType.AP, "1234567890");
        assertNotNull(result);
        assertEquals(IdentificadorType.AP, result.getTipo());
        assertEquals("1234567890", result.getIdentificador());
    }

    @Test
    void createInvalidType() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            IdentificadorFactory.create(null, "123");
        });
        assertEquals("Tipo de identificador não pode ser nulo", exception.getMessage());
    }

    @Test
    void createPessoaFisicaInvalidLength() {
        CustomException exception = assertThrows(CustomException.class, () -> {
            IdentificadorFactory.create(IdentificadorType.PF, "123");
        });
        assertEquals("Identificador Pessoa Física deve ter 11 caracteres!", exception.getMessage());
    }

    @Test
    void createPessoaJuridicaInvalidLength() {
        CustomException exception = assertThrows(CustomException.class, () -> {
            IdentificadorFactory.create(IdentificadorType.PJ, "123");
        });
        assertEquals("Identificador Pessoa Jurídica deve ter 14 caracteres!", exception.getMessage());
    }

    @Test
    void createEstudanteUniversitarioInvalidLength() {
        CustomException exception = assertThrows(CustomException.class, () -> {
            IdentificadorFactory.create(IdentificadorType.EU, "123");
        });
        assertEquals("Identificador Estudante Universitário deve ter 8 caracteres!", exception.getMessage());
    }

    @Test
    void createAposentadoInvalidLength() {
        CustomException exception = assertThrows(CustomException.class, () -> {
            IdentificadorFactory.create(IdentificadorType.AP, "123");
        });
        assertEquals("Identificador Aposentado deve ter 10 caracteres!", exception.getMessage());
    }

    @Test
    void createNullIdentificador() {
        CustomException exception = assertThrows(CustomException.class, () -> {
            IdentificadorFactory.create(IdentificadorType.PF, null);
        });
        assertEquals("Identificador não pode ser nulo", exception.getMessage());
    }
}