package com.x.loan.domain.usecase.pessoa;

import com.x.loan.domain.model.PessoaDomain;
import com.x.loan.domain.service.PessoaDomainService;

public class PessoaUpdate {

    private final PessoaDomainService pessoaDomainService;

    public PessoaUpdate(PessoaDomainService pessoaDomainService) {
        this.pessoaDomainService = pessoaDomainService;
    }

    public PessoaDomain execute(PessoaDomain pessoa) {
        return pessoaDomainService.update(pessoa);
    }
}
