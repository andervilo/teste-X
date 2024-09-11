package com.x.loan.domain.service;

import com.x.loan.domain.model.PessoaDomain;

import java.util.List;

public interface PessoaDomainService {
    PessoaDomain save(PessoaDomain pessoa);
    PessoaDomain update(PessoaDomain pessoa);
    void delete(Long id);
    PessoaDomain findById(Long id);
    List<PessoaDomain> findAll();
    PessoaDomain findByIdentificador(String identificador);
}
