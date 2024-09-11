package com.x.loan.domain.factory;

import com.x.loan.domain.enumeration.IdentificadorType;
import com.x.loan.domain.exception.CustomException;
import com.x.loan.domain.validator.APValidator;
import com.x.loan.domain.validator.CNPJValidator;
import com.x.loan.domain.validator.CPFValidator;
import com.x.loan.domain.validator.EUValidator;
import com.x.loan.domain.vo.*;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Objects;

@Slf4j
public class IdentificadorFactory {

    public static final BigDecimal PF_VALOR_MAXIMO_TOTAL_EMPRESTIMO = new BigDecimal(10000);
    public static final BigDecimal PF_VALOR_MINIMO_MENSAL_PARCELA = new BigDecimal(300);

    public static final BigDecimal PJ_VALOR_MAXIMO_TOTAL_EMPRESTIMO = new BigDecimal(100000);
    public static final BigDecimal PJ_VALOR_MINIMO_MENSAL_PARCELA = new BigDecimal(1000);

    public static final BigDecimal EU_VALOR_MAXIMO_TOTAL_EMPRESTIMO = new BigDecimal(10000);
    public static final BigDecimal EU_VALOR_MINIMO_MENSAL_PARCELA = new BigDecimal(100);

    public static final BigDecimal AP_VALOR_MAXIMO_TOTAL_EMPRESTIMO = new BigDecimal(25000);
    public static final BigDecimal AP_VALOR_MINIMO_MENSAL_PARCELA = new BigDecimal(400);



    public static IdentificadorBaseVO create(IdentificadorType tipo, String identificador) {
        if (Objects.isNull(tipo)) {
            throw new IllegalArgumentException("Tipo de identificador não pode ser nulo");
        }
        return switch (tipo) {
            case PF -> getPeessoaFisica(identificador);
            case PJ -> getPessoaJuridica(identificador);
            case EU -> getEstudanteUniversitario(identificador);
            case AP -> getAposentado(identificador);
            default -> null;
        };
    }

    private static IdentificadorBaseVO getAposentado(String identificador) {
        validateAP(identificador);
        return AposentadoIdentBaseVO.builder()
                .identificador(identificador)
                .tipo(IdentificadorType.AP)
                .valorMinimoMensalParcela(AP_VALOR_MINIMO_MENSAL_PARCELA)
                .valorMaximoTotalEmprestimo(AP_VALOR_MAXIMO_TOTAL_EMPRESTIMO)
                .build();
    }

    private static IdentificadorBaseVO getEstudanteUniversitario(String identificador) {
        validateEU(identificador);
        return EstudanteUniversitarioIdentBaseVO.builder()
                .identificador(identificador)
                .tipo(IdentificadorType.EU)
                .valorMinimoMensalParcela(EU_VALOR_MINIMO_MENSAL_PARCELA)
                .valorMaximoTotalEmprestimo(EU_VALOR_MAXIMO_TOTAL_EMPRESTIMO)
                .build();
    }

    private static IdentificadorBaseVO getPessoaJuridica(String identificador) {
        validatePJ(identificador);
        return PessoaJuridicaIdentVO.builder()
                .identificador(identificador)
                .tipo(IdentificadorType.PJ)
                .valorMinimoMensalParcela(PJ_VALOR_MINIMO_MENSAL_PARCELA)
                .valorMaximoTotalEmprestimo(PJ_VALOR_MAXIMO_TOTAL_EMPRESTIMO)
                .build();
    }

    private static IdentificadorBaseVO getPeessoaFisica(String identificador) {
        validatePF(identificador);
        return PessoaFisicaIdentVO.builder()
                .identificador(identificador)
                .tipo(IdentificadorType.PF)
                .valorMinimoMensalParcela(PF_VALOR_MINIMO_MENSAL_PARCELA)
                .valorMaximoTotalEmprestimo(PF_VALOR_MAXIMO_TOTAL_EMPRESTIMO)
                .build();
    }

    private static void isIdentificadorNull(String identificador) {
        if (identificador == null) {
            log.error("Identificador não pode ser nulo");
            throw new CustomException(400, "Identificador não pode ser nulo");
        }
    }

    private static void validatePF(String identificador) {
        isIdentificadorNull(identificador);
        if (identificador.length() != 11) {
            throw new CustomException(400, "Identificador Pessoa Física deve ter 11 caracteres!");
        }

        if(!CPFValidator.isValidCPF(identificador)){
            throw new CustomException(400,"CPF inválido!");
        }

    }

    private static void validatePJ(String identificador) {
        isIdentificadorNull(identificador);
        if (identificador.length() != 14) {
            throw new CustomException(400,"Identificador Pessoa Jurídica deve ter 14 caracteres!");
        }

        if(!CNPJValidator.isValidCNPJ(identificador)){
            throw new CustomException(400,"CNPJ inválido!");
        }
    }

    private static void validateEU(String identificador) {
        isIdentificadorNull(identificador);
        if (identificador.length() != 8) {
            throw new CustomException(400,"Identificador Estudante Universitário deve ter 8 caracteres!");
        }

        if(!EUValidator.isValidEU(identificador)){
            throw new CustomException(400,"Número de Estudante Universitário inválido!");
        }
    }

    private static void validateAP(String identificador) {
        isIdentificadorNull(identificador);
        if (identificador.length() != 10) {
            throw new CustomException(400,"Identificador Aposentado deve ter 10 caracteres!");
        }

        if(!APValidator.isValidAP(identificador)){
            throw new CustomException(400,"Número de Aposentado inválido!");
        }
    }

}
