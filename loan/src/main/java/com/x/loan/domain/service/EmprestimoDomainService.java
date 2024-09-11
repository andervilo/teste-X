package com.x.loan.domain.service;

import com.x.loan.domain.model.EmprestimoDomain;
import com.x.loan.domain.model.PessoaDomain;

import java.util.List;

public interface EmprestimoDomainService {

    List<EmprestimoDomain> findAll();

    List<EmprestimoDomain> findByPessoa(PessoaDomain pessoaDomain);

    EmprestimoDomain findById(Long id);

    EmprestimoDomain create(EmprestimoDomain emprestimoDomain);

    Boolean pay(Long id);
}
