package com.x.loan.domain.vo;

import com.x.loan.domain.enumeration.IdentificadorType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IdentificadorBaseVO {

    protected String identificador;

    protected IdentificadorType tipo;

    protected BigDecimal valorMinimoMensalParcela;

    protected BigDecimal valorMaximoTotalEmprestimo;
}
