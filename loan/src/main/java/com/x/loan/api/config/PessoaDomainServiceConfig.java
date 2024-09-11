package com.x.loan.api.config;

import com.x.loan.domain.service.PessoaDomainService;
import com.x.loan.domain.usecase.emprestimo.EmprestimoFindByPessoa;
import com.x.loan.domain.usecase.pessoa.*;
import com.x.loan.infra.service.EmprestimoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PessoaDomainServiceConfig {

    private final PessoaDomainService pessoaDomainService;
    private final EmprestimoService emprestimoService;

    @Bean
    public PessoaCreate pessoaCreate() {
        return new PessoaCreate(pessoaDomainService);
    }

    @Bean
    public PessoaUpdate pessoaUpdate() {
        return new PessoaUpdate(pessoaDomainService);
    }

    @Bean
    public EmprestimoFindByPessoa emprestimoFindByPessoa() {
        return new EmprestimoFindByPessoa(emprestimoService);
    }

    @Bean
    public PessoaDelete pessoaDelete() {
        return new PessoaDelete(pessoaDomainService, emprestimoFindByPessoa());
    }

    @Bean
    public PessoaFindAll pessoaFindAll() {
        return new PessoaFindAll(pessoaDomainService);
    }

    @Bean
    public PessoaFindById pessoaFindById() {
        return new PessoaFindById(pessoaDomainService);
    }

    @Bean
    public PessoaFindByIdentificador pessoaFindByIdentificador() {
        return new PessoaFindByIdentificador(pessoaDomainService);
    }
}
