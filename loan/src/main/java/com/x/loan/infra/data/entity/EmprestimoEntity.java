package com.x.loan.infra.data.entity;

import com.x.loan.domain.enumeration.StatusPagamentoType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "emprestimo")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmprestimoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PessoaEntity pessoa;

    private BigDecimal valorEmprestimo;

    private Integer numeroParcelas;

    private StatusPagamentoType statusPgamento;

    private LocalDate dataCriacao;
}
