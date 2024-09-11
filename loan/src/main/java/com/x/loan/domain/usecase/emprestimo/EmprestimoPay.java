package com.x.loan.domain.usecase.emprestimo;

import com.x.loan.domain.enumeration.TipoReajusteType;
import com.x.loan.domain.model.EmprestimoDomain;
import com.x.loan.domain.model.PessoaDomain;
import com.x.loan.domain.service.EmprestimoDomainService;
import com.x.loan.domain.service.PessoaDomainService;
import com.x.loan.domain.usecase.pessoa.PessoaUpdate;

public class EmprestimoPay {

    private final EmprestimoDomainService emprestimoDomainService;
    private final PessoaUpdate pessoaUpdate;
    private final EmprestimoFindById emprestimoFindById;

    public EmprestimoPay(EmprestimoDomainService emprestimoDomainService, PessoaUpdate pessoaUpdate, EmprestimoFindById emprestimoFindById) {
        this.emprestimoDomainService = emprestimoDomainService;
        this.pessoaUpdate = pessoaUpdate;
        this.emprestimoFindById = emprestimoFindById;
    }
    public Boolean execute(Long id) {
        EmprestimoDomain emprestimoDomain = emprestimoFindById.execute(id);
        Boolean result = emprestimoDomainService.pay(id);
        if(result) {
            PessoaDomain pessoaDomain = emprestimoDomain.getPessoa();
            pessoaDomain.reajustarValorMaximoEmprestimo(emprestimoDomain.getValorEmprestimo(), TipoReajusteType.AUMENTAR);
            pessoaUpdate.execute(pessoaDomain);
            return true;
        }
        return emprestimoDomainService.pay(id);
    }
}
