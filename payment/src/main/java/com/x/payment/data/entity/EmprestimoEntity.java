package com.x.payment.data.entity;

import com.x.payment.data.enumeration.StatusPagamentoType;
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

    private BigDecimal valorEmprestimo;

    private Integer numeroParcelas;

    @Enumerated
    private StatusPagamentoType statusPgamento;

    private LocalDate dataCriacao;
}
