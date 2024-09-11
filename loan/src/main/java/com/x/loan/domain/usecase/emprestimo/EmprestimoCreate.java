package com.x.loan.domain.usecase.emprestimo;

import com.x.loan.domain.model.EmprestimoDomain;
import com.x.loan.domain.service.EmprestimoDomainService;

public class EmprestimoCreate {

    private final EmprestimoDomainService emprestimoDomainService;

    public EmprestimoCreate(EmprestimoDomainService emprestimoDomainService) {
        this.emprestimoDomainService = emprestimoDomainService;
    }
    public EmprestimoDomain execute(EmprestimoDomain emprestimoDomain) {
        return emprestimoDomainService.create(emprestimoDomain);
    }
}
