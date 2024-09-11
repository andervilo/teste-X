package com.x.loan.api.mapper;

import com.x.loan.api.dto.EmprestimoRequest;
import com.x.loan.api.dto.EmprestimoResponse;
import com.x.loan.domain.factory.EmprestimoDomainFactory;
import com.x.loan.domain.model.EmprestimoDomain;
import com.x.loan.domain.model.PessoaDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EmprestimoRequestResponseMapper {



    public EmprestimoDomain toDomain(EmprestimoRequest emprestimoRequest, PessoaDomain pessoaDomain) {
        return EmprestimoDomainFactory.create(pessoaDomain, emprestimoRequest.getValorEmprestimo(), emprestimoRequest.getNumeroParcelas());
    }

    public EmprestimoResponse toResponse(EmprestimoDomain emprestimoDomain) {
        return EmprestimoResponse.builder()
                .id(emprestimoDomain.getId())
                .identificador(emprestimoDomain.getPessoa().getIdentificador().getIdentificador())
                .nome(emprestimoDomain.getPessoa().getNome())
                .valorEmprestimo(emprestimoDomain.getValorEmprestimo())
                .numeroParcelas(emprestimoDomain.getNumeroParcelas())
                .valorParcela(calcularValorParcela(emprestimoDomain.getValorEmprestimo(), emprestimoDomain.getNumeroParcelas()))
                .dataContratacao(emprestimoDomain.getDataCriacao())
                .statusPagamento(emprestimoDomain.getStatusPagamento())
                .build();
    }

    public List<EmprestimoResponse> toResponseList(List<EmprestimoDomain> emprestimos) {
        return emprestimos.stream().map(this::toResponse).toList();
    }

    private BigDecimal calcularValorParcela(BigDecimal valorEmprestimo, Integer numeroParcelas) {
        return valorEmprestimo.divide(BigDecimal.valueOf(numeroParcelas), 2, RoundingMode.HALF_UP);
    }
}
