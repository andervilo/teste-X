package com.x.loan.domain.factory;

import com.x.loan.domain.enumeration.StatusPagamentoType;
import com.x.loan.domain.exception.CustomException;
import com.x.loan.domain.model.EmprestimoDomain;
import com.x.loan.domain.model.PessoaDomain;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmprestimoDomainFactoryTest {

    @Test
    void createEmprestimoDomainWithAllDetails() {
        PessoaDomain pessoa = new PessoaDomain();
        BigDecimal valorEmprestimo = new BigDecimal("1000.00");
        Integer numeroParcelas = 12;
        StatusPagamentoType statusPagamento = StatusPagamentoType.PENDENTE;
        LocalDate dataCriacao = LocalDate.now();

        EmprestimoDomain result = EmprestimoDomainFactory.create(1L, pessoa, valorEmprestimo, numeroParcelas, statusPagamento, dataCriacao);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(pessoa, result.getPessoa());
        assertEquals(valorEmprestimo, result.getValorEmprestimo());
        assertEquals(numeroParcelas, result.getNumeroParcelas());
        assertEquals(statusPagamento, result.getStatusPagamento());
        assertEquals(dataCriacao, result.getDataCriacao());
    }

    @Test
    void createEmprestimoDomainWithNullPessoa() {
        BigDecimal valorEmprestimo = new BigDecimal("1000.00");
        Integer numeroParcelas = 12;

        CustomException exception = assertThrows(CustomException.class, () -> {
            EmprestimoDomainFactory.create(null, valorEmprestimo, numeroParcelas);
        });

        assertEquals("Pessoa não pode ser nula", exception.getMessage());
    }

    @Test
    void createEmprestimoDomainWithNullValorEmprestimo() {
        PessoaDomain pessoa = new PessoaDomain();
        Integer numeroParcelas = 12;

        CustomException exception = assertThrows(CustomException.class, () -> {
            EmprestimoDomainFactory.create(pessoa, null, numeroParcelas);
        });

        assertEquals("Valor do emprestimo não pode ser nulo", exception.getMessage());
    }

    @Test
    void createEmprestimoDomainWithNullNumeroParcelas() {
        PessoaDomain pessoa = new PessoaDomain();
        BigDecimal valorEmprestimo = new BigDecimal("1000.00");

        CustomException exception = assertThrows(CustomException.class, () -> {
            EmprestimoDomainFactory.create(pessoa, valorEmprestimo, null);
        });

        assertEquals("Número de parcelas não pode ser nulo", exception.getMessage());
    }
}