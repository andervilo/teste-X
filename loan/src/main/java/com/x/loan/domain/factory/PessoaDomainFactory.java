package com.x.loan.domain.factory;

import com.x.loan.domain.model.PessoaDomain;
import com.x.loan.domain.vo.IdentificadorBaseVO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PessoaDomainFactory {
    public static PessoaDomain create(String nome, LocalDate dataNascimento, IdentificadorBaseVO identificador) {
        return new PessoaDomain(nome, dataNascimento, identificador);
    }

    public static PessoaDomain create(Long id, String nome, LocalDate dataNascimento, IdentificadorBaseVO identificador, BigDecimal valorMinimoMensal, BigDecimal valorMaximoEmprestimo) {
        return new PessoaDomain(id, nome, dataNascimento, identificador, valorMinimoMensal, valorMaximoEmprestimo);
    }
}
