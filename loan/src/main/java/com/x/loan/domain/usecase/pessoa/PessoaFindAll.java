package com.x.loan.domain.usecase.pessoa;

import com.x.loan.domain.model.PessoaDomain;
import com.x.loan.domain.service.PessoaDomainService;

import java.util.List;

public class PessoaFindAll {

    private final PessoaDomainService pessoaDomainService;

    public PessoaFindAll(PessoaDomainService pessoaDomainService) {
        this.pessoaDomainService = pessoaDomainService;
    }

    public List<PessoaDomain> execute() {
        return pessoaDomainService.findAll();
    }
}
