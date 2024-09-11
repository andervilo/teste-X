package com.x.loan.api.dto;

import com.x.loan.api.constants.ValidateMessages;
import com.x.loan.api.validator.ValidIdentificadorType;
import com.x.loan.domain.enumeration.IdentificadorType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaCreateRequest {

    @NotBlank(message = ValidateMessages.CAMPO_NOME_OBRIGATORIO)
    private String nome;

    private LocalDate dataNascimento;

    @ValidIdentificadorType
    private IdentificadorType tipoIdentificador;

    @NotBlank(message = ValidateMessages.CAMPO_IDENTIFICADOR_OBRIGATORIO)
    @NotNull(message = ValidateMessages.CAMPO_IDENTIFICADOR_OBRIGATORIO)
    @Size(min = 8, max = 14, message = ValidateMessages.CAMPO_IDENTIFICADOR_CARACTERES)
    @Pattern(regexp = "\\d+", message = ValidateMessages.CAMPO_IDENTIFICADDOR_APENAS_NUMEROS)
    private String identificador;
}
