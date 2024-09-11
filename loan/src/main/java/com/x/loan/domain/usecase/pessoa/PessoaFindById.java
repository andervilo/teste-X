package com.x.loan.domain.usecase.pessoa;

import com.x.loan.domain.model.PessoaDomain;
import com.x.loan.domain.service.PessoaDomainService;

public class PessoaFindById{

    private final PessoaDomainService pessoaDomainService;

    public PessoaFindById(PessoaDomainService pessoaDomainService) {
        this.pessoaDomainService = pessoaDomainService;
    }

    public PessoaDomain execute(Long id) {
        return pessoaDomainService.findById(id);
    }
}
