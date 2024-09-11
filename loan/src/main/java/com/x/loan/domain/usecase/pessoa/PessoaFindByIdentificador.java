package com.x.loan.domain.usecase.pessoa;

import com.x.loan.domain.model.PessoaDomain;
import com.x.loan.domain.service.PessoaDomainService;

public class PessoaFindByIdentificador {

    private final PessoaDomainService pessoaDomainService;

    public PessoaFindByIdentificador(PessoaDomainService pessoaDomainService) {
        this.pessoaDomainService = pessoaDomainService;
    }

    public PessoaDomain execute(String identificador) {
        return pessoaDomainService.findByIdentificador(identificador);
    }
}
