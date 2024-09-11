package com.x.loan.domain.usecase.pessoa;

import com.x.loan.domain.model.PessoaDomain;
import com.x.loan.domain.service.PessoaDomainService;

public class PessoaCreate{

    private final PessoaDomainService pessoaDomainService;

    public PessoaCreate(PessoaDomainService pessoaDomainService) {
        this.pessoaDomainService = pessoaDomainService;
    }

    public PessoaDomain execute(PessoaDomain pessoa) {
        return pessoaDomainService.save(pessoa);
    }
}
