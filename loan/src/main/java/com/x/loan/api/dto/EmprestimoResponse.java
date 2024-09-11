package com.x.loan.api.dto;

import com.x.loan.domain.enumeration.StatusPagamentoType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmprestimoResponse {
    private Long id;
    private String identificador;
    private String nome;
    private BigDecimal valorEmprestimo;
    private Integer numeroParcelas;
    private BigDecimal valorParcela;
    private LocalDate dataContratacao;
    private StatusPagamentoType statusPagamento;
}
