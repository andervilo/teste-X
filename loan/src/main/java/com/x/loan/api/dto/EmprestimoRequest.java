package com.x.loan.api.dto;

import com.x.loan.api.constants.ValidateMessages;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmprestimoRequest {

    @NotBlank(message = ValidateMessages.CAMPO_IDENTIFICADOR_OBRIGATORIO)
    @NotNull(message = ValidateMessages.CAMPO_IDENTIFICADOR_OBRIGATORIO)
    @Size(min = 8, max = 14, message = ValidateMessages.CAMPO_IDENTIFICADOR_CARACTERES)
    @Pattern(regexp = "\\d+", message = ValidateMessages.CAMPO_IDENTIFICADDOR_APENAS_NUMEROS)
    private String identificador;

    @NotNull(message = ValidateMessages.CAMPO_VALOR_DO_EMPRESTIMO_OBRIGATORIO)
    @DecimalMin(value = "0.0", inclusive = false, message = ValidateMessages.VALOR_DEVE_SER_MAIOR_QUE_ZERO)
    private BigDecimal valorEmprestimo;

    @NotNull(message = ValidateMessages.CAMPO_NUMERO_DE_PARCELAS_OBRIGATORIO)
    @Max(value = 24, message = ValidateMessages.CAMPO_NUMERO_PARCELAS)
    @Min(value = 1, message = ValidateMessages.CAMPO_NUMERO_PARCELAS)
    private Integer numeroParcelas;
}
