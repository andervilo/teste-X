package com.x.loan.api.config;

import com.x.loan.domain.usecase.emprestimo.*;
import com.x.loan.domain.usecase.pessoa.PessoaUpdate;
import com.x.loan.infra.service.EmprestimoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class EmprestimoDomainServiceConfig {

    private final EmprestimoService emprestimoService;
    private final PessoaUpdate pessoaUpdate;


    @Bean
    public EmprestimoCreate emprestimoCreate() {
        return new EmprestimoCreate(emprestimoService);
    }

    @Bean
    public EmprestimoFindAll emprestimoFindAll() {
        return new EmprestimoFindAll(emprestimoService);
    }

    @Bean
    public EmprestimoFindById emprestimoFindById() {
        return new EmprestimoFindById(emprestimoService);
    }

    @Bean
    public EmprestimoPay emprestimoPay() {
        return new EmprestimoPay(emprestimoService, pessoaUpdate, emprestimoFindById());
    }


}
