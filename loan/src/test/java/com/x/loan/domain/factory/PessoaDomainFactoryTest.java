package com.x.loan.domain.factory;

import static org.junit.jupiter.api.Assertions.*;

import com.x.loan.domain.enumeration.IdentificadorType;
import com.x.loan.domain.model.PessoaDomain;
import com.x.loan.domain.vo.IdentificadorBaseVO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

class PessoaDomainFactoryTest {

    @Test
    void createPessoaDomainWithBasicDetails() {
        IdentificadorBaseVO identificador = IdentificadorFactory.create(IdentificadorType.PF,"72316057020" );
        PessoaDomain result = PessoaDomainFactory.create("João", LocalDate.of(1990, 1, 1), identificador);
        assertNotNull(result);
        assertEquals("João", result.getNome());
        assertEquals(LocalDate.of(1990, 1, 1), result.getDataNascimento());
        assertEquals(identificador, result.getIdentificador());
    }

    @Test
    void createPessoaDomainWithAllDetails() {
        IdentificadorBaseVO identificador = IdentificadorFactory.create(IdentificadorType.PF,"72316057020" );
        PessoaDomain result = PessoaDomainFactory.create(1L, "João", LocalDate.of(1990, 1, 1), identificador, new BigDecimal("1000.00"), new BigDecimal("5000.00"));
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("João", result.getNome());
        assertEquals(LocalDate.of(1990, 1, 1), result.getDataNascimento());
        assertEquals(identificador, result.getIdentificador());
        assertEquals(new BigDecimal("1000.00"), result.getValorMinimoMensal());
        assertEquals(new BigDecimal("5000.00"), result.getValorMaximoEmprestimo());
    }

    @Test
    void createPessoaDomainWithNullName() {
        IdentificadorBaseVO identificador = IdentificadorFactory.create(IdentificadorType.PF,"72316057020" );
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            PessoaDomainFactory.create(null, LocalDate.of(1990, 1, 1), identificador);
        });
        assertEquals("Nome não pode ser nulo", exception.getMessage());
    }

    @Test
    void createPessoaDomainWithNullIdentificador() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            PessoaDomainFactory.create("João", LocalDate.of(1990, 1, 1), null);
        });
        assertEquals("Identificador não pode ser nulo", exception.getMessage());
    }
}