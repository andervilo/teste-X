package com.x.loan.domain.usecase.emprestimo;

import com.x.loan.domain.model.EmprestimoDomain;
import com.x.loan.domain.service.EmprestimoDomainService;

import java.util.List;

public class EmprestimoFindAll {

    private final EmprestimoDomainService emprestimoDomainService;

    public EmprestimoFindAll(EmprestimoDomainService emprestimoDomainService) {
        this.emprestimoDomainService = emprestimoDomainService;
    }
    public List<EmprestimoDomain> execute() {

        return emprestimoDomainService.findAll();
    }
}
