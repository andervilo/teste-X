package com.x.loan.domain.usecase.pessoa;

import com.x.loan.domain.exception.CustomException;
import com.x.loan.domain.model.EmprestimoDomain;
import com.x.loan.domain.service.PessoaDomainService;
import com.x.loan.domain.usecase.emprestimo.EmprestimoFindByPessoa;

import java.util.List;

public class PessoaDelete{

    private final PessoaDomainService pessoaDomainService;
    private final EmprestimoFindByPessoa emprestimoFindByPessoa;

    public PessoaDelete(PessoaDomainService pessoaDomainService, EmprestimoFindByPessoa emprestimoFindByPessoa) {
        this.pessoaDomainService = pessoaDomainService;
        this.emprestimoFindByPessoa = emprestimoFindByPessoa;
    }

    public Void execute(Long id) {
        List<EmprestimoDomain> emprestimos = emprestimoFindByPessoa.execute(pessoaDomainService.findById(id));
        if(!emprestimos.isEmpty()){
            throw new CustomException(400,"Pessoa possui empréstimos ativos!");
        }
        pessoaDomainService.delete(id);
        return null;
    }
}
