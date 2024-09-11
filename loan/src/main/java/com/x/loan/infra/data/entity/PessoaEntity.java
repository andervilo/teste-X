package com.x.loan.infra.data.entity;

import com.x.loan.domain.enumeration.IdentificadorType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pessoas")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PessoaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "identificador", nullable = false, unique = true)
    private String identificador;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "tipo_identificador", nullable = false)
    @Enumerated(EnumType.STRING)
    private IdentificadorType tipoIdentificador;

    @Column(name = "valor_min_mensal", precision = 18,  scale = 2, nullable = false)
    private BigDecimal valorMinimoMensal;

    @Column(name = "valor_max_emprestimo", precision = 18,  scale = 2, nullable = false)
    private BigDecimal valorMaximoEmprestimo;
}
