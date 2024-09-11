package com.x.loan.api.dto;

import com.x.loan.domain.enumeration.IdentificadorType;
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
public class PessoaCreateResponse {

    private Long id;

    private String nome;

    private LocalDate dataNascimento;

    private IdentificadorType tipoIdentificador;

    private String identificador;

    private BigDecimal valorMinimoMensalParcela;

    private BigDecimal valorMaximoTotalEmprestimo;
}
