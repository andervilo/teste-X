package com.x.loan.domain.usecase.emprestimo;

import com.x.loan.domain.model.EmprestimoDomain;
import com.x.loan.domain.service.EmprestimoDomainService;

public class EmprestimoFindById {

    private final EmprestimoDomainService emprestimoDomainService;

    public EmprestimoFindById(EmprestimoDomainService emprestimoDomainService) {
        this.emprestimoDomainService = emprestimoDomainService;
    }

    public EmprestimoDomain execute(Long id) {

        return emprestimoDomainService.findById(id);
    }
}
