package com.x.loan.domain.model;

 import com.x.loan.domain.enumeration.TipoReajusteType;
 import com.x.loan.domain.vo.IdentificadorBaseVO;
 import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
 import lombok.extern.slf4j.Slf4j;

 import java.math.BigDecimal;
 import java.time.LocalDate;

@Data
@NoArgsConstructor
@Builder
@Slf4j
public class PessoaDomain {

    private Long id;

    private String nome;

    private LocalDate dataNascimento;

    private IdentificadorBaseVO identificador;

    private BigDecimal valorMinimoMensal;

    private BigDecimal valorMaximoEmprestimo;

    public PessoaDomain(String nome, LocalDate dataNascimento, IdentificadorBaseVO identificador) {
        validateIdentificador(identificador);
        validaNome(nome);
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.identificador = identificador;
        this.valorMinimoMensal = identificador.getValorMinimoMensalParcela();
        this.valorMaximoEmprestimo = identificador.getValorMaximoTotalEmprestimo();
    }

    public PessoaDomain(Long id, String nome, LocalDate dataNascimento, IdentificadorBaseVO identificador, BigDecimal valorMinimoMensal, BigDecimal valorMaximoEmprestimo) {
        validateIdentificador(identificador);
        validaNome(nome);
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.identificador = identificador;
        this.valorMinimoMensal = valorMinimoMensal;
        this.valorMaximoEmprestimo = valorMaximoEmprestimo;
        validaValorMinimoMensal(valorMinimoMensal);
        validaValorMaximoEmprestimo(valorMaximoEmprestimo);

    }

    public void reajustarValorMaximoEmprestimo(BigDecimal valorReajuste, TipoReajusteType tipoReajuste) {
        validaReajuste(valorReajuste, tipoReajuste);
        if (tipoReajuste == TipoReajusteType.AUMENTAR) this.valorMaximoEmprestimo = this.valorMaximoEmprestimo.add(valorReajuste);
        if (tipoReajuste == TipoReajusteType.DIMINUIR) this.valorMaximoEmprestimo = this.valorMaximoEmprestimo.subtract(valorReajuste);
    }

    private void validaValorMinimoMensal(BigDecimal valorMinimoMensal) {
        if(valorMinimoMensal.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Valor mínimo mensal deve ser maior que zero");

        if(this.valorMinimoMensal.compareTo(identificador.getValorMinimoMensalParcela()) < 0)
            throw new IllegalArgumentException("Valor mínimo mensal não pode ser menor que " + identificador.getValorMinimoMensalParcela());
    }

    private void validaValorMaximoEmprestimo(BigDecimal valorMaximoEmprestimo) {
        if(this.valorMaximoEmprestimo.compareTo(identificador.getValorMaximoTotalEmprestimo()) > 0)
            throw new IllegalArgumentException("Valor máximo de empréstimo não pode ser maior que " + identificador.getValorMaximoTotalEmprestimo());
    }

    private void validaReajuste(BigDecimal valorReajuste, TipoReajusteType tipoReajuste) {
        if(valorReajuste.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Valor de reajuste deve ser maior que zero");

        if(tipoReajuste.equals(TipoReajusteType.AUMENTAR) && this.valorMaximoEmprestimo.add(valorReajuste).compareTo(identificador.getValorMaximoTotalEmprestimo()) > 0)
            throw new IllegalArgumentException("Reajuste não executado. Valor máximo de empréstimo não pode ser maior que " + identificador.getValorMaximoTotalEmprestimo());

        if(tipoReajuste.equals(TipoReajusteType.DIMINUIR) && this.valorMaximoEmprestimo.subtract(valorReajuste).compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Reajuste não executado. Valor máximo de empréstimo não pode ser menor que zero");
    }

    private void validaNome(String nome) {
        if (nome == null) {
            log.error("Nome não pode ser nulo");
            throw new IllegalArgumentException("Nome não pode ser nulo");
        }
    }

    private void validateIdentificador(IdentificadorBaseVO identificador) {
        if (identificador == null) {
            log.error("Identificador não pode ser nulo");
            throw new IllegalArgumentException("Identificador não pode ser nulo");
        }

        if (identificador.getTipo() == null) {
            throw new IllegalArgumentException("Tipo de identificador não pode ser nulo");
        }
        if (identificador.getIdentificador() == null) {
            log.error("Valor do Identificador não pode ser nulo");
            throw new IllegalArgumentException("Valor do Identificador não pode ser nulo");
        }
    }
}
