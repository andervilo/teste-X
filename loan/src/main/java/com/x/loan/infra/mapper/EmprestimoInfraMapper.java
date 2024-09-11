package com.x.loan.infra.mapper;

import com.x.loan.domain.enumeration.StatusPagamentoType;
import com.x.loan.domain.factory.EmprestimoDomainFactory;
import com.x.loan.domain.model.EmprestimoDomain;
import com.x.loan.domain.model.PessoaDomain;
import com.x.loan.infra.data.entity.EmprestimoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmprestimoInfraMapper {

    private final PessoaInfraMapper pessoaInfraMapper;

    public EmprestimoDomain toDomain(EmprestimoEntity emprestimoEntity){
        return EmprestimoDomainFactory.create(
                emprestimoEntity.getId(),
                pessoaInfraMapper.toDomain(emprestimoEntity.getPessoa()),
                emprestimoEntity.getValorEmprestimo(),
                emprestimoEntity.getNumeroParcelas(),
                emprestimoEntity.getStatusPgamento(),
                emprestimoEntity.getDataCriacao()
        );
    }

    public List<EmprestimoDomain> toDomainList(List<EmprestimoEntity> emprestimos){
        return emprestimos.stream().map(this::toDomain).toList();
    }

    public EmprestimoEntity toEntity(EmprestimoDomain emprestimoDomain) {
        return EmprestimoEntity.builder()
                .id(emprestimoDomain.getId())
                .pessoa(pessoaInfraMapper.toEntity(emprestimoDomain.getPessoa()))
                .valorEmprestimo(emprestimoDomain.getValorEmprestimo())
                .numeroParcelas(emprestimoDomain.getNumeroParcelas())
                .statusPgamento(emprestimoDomain.getStatusPagamento())
                .dataCriacao(emprestimoDomain.getDataCriacao())
                .build();
    }
}
