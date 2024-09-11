package com.x.loan.domain.factory;

import com.x.loan.domain.enumeration.StatusPagamentoType;
import com.x.loan.domain.model.EmprestimoDomain;
import com.x.loan.domain.model.PessoaDomain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmprestimoDomainFactory {
    public static EmprestimoDomain create(PessoaDomain pessoa, BigDecimal valorEmprestimo, Integer numeroParcelas) {
        return new EmprestimoDomain(pessoa, valorEmprestimo, numeroParcelas);
    }

    public static EmprestimoDomain create(Long id, PessoaDomain pessoa, BigDecimal valorEmprestimo, Integer numeroParcelas, StatusPagamentoType statusPagamento, LocalDate dataCriacao) {
        return new EmprestimoDomain(id, pessoa, valorEmprestimo, numeroParcelas, statusPagamento, dataCriacao);
    }
}
