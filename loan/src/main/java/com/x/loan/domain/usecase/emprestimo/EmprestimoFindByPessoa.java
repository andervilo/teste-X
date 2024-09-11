package com.x.loan.domain.usecase.emprestimo;

import com.x.loan.domain.enumeration.TipoReajusteType;
import com.x.loan.domain.model.EmprestimoDomain;
import com.x.loan.domain.model.PessoaDomain;
import com.x.loan.domain.service.EmprestimoDomainService;
import com.x.loan.domain.usecase.pessoa.PessoaUpdate;

import java.util.List;

public class EmprestimoFindByPessoa {

    private final EmprestimoDomainService emprestimoDomainService;

    public EmprestimoFindByPessoa(EmprestimoDomainService emprestimoDomainService) {
        this.emprestimoDomainService = emprestimoDomainService;
    }
    public List<EmprestimoDomain> execute(PessoaDomain pessoaDomain) {
        return emprestimoDomainService.findByPessoa(pessoaDomain);
    }
}
