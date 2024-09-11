package com.x.loan.domain.model;

import com.x.loan.domain.enumeration.StatusPagamentoType;
import com.x.loan.domain.exception.CustomException;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Data
public class EmprestimoDomain {

    private Long id;

    private PessoaDomain pessoa;

    private BigDecimal valorEmprestimo;

    private Integer numeroParcelas;

    private StatusPagamentoType statusPagamento;

    private LocalDate dataCriacao;

    public EmprestimoDomain(Long id, PessoaDomain pessoa, BigDecimal valorEmprestimo, Integer numeroParcelas, StatusPagamentoType statusPagamento, LocalDate dataCriacao) {
        this.id = id;
        this.pessoa = pessoa;
        this.valorEmprestimo = valorEmprestimo;
        this.numeroParcelas = numeroParcelas;
        this.statusPagamento = statusPagamento;
        this.dataCriacao = dataCriacao;
        validateEmprestimo();
    }

    public EmprestimoDomain(PessoaDomain pessoa, BigDecimal valorEmprestimo, Integer numeroParcelas) {
        this.pessoa = pessoa;
        this.valorEmprestimo = valorEmprestimo;
        this.numeroParcelas = numeroParcelas;
        this.dataCriacao = LocalDate.now();
        this.statusPagamento = StatusPagamentoType.PENDENTE;
        validateEmprestimo();
        validaNumeroParcelas();
        validaValorMaximoParcela();
        validaValorMinimoParcela();
        validaValorEmprestimo();
    }

    private void validaNumeroParcelas() {
        if (numeroParcelas < 1 || numeroParcelas > 24) {
            throw new CustomException(400, "Número de parcelas deve ser entre 1 e 24!");
        }
    }

    private void validaValorMinimoParcela() {
        if (valorEmprestimo.divide(BigDecimal.valueOf(numeroParcelas), 2, RoundingMode.HALF_UP)
                .compareTo(pessoa.getIdentificador().getValorMinimoMensalParcela()) < 0) {
            throw new CustomException(400, "Valor da parcela deve ser no mínimo R$ " + pessoa.getIdentificador().getValorMinimoMensalParcela());
        }
    }

    private void validaValorMaximoParcela() {
        if (valorEmprestimo.divide(BigDecimal.valueOf(numeroParcelas), 2, RoundingMode.HALF_UP)
                .compareTo(pessoa.getValorMaximoEmprestimo()) > 0) {
            throw new CustomException(400, "Valor da parcela deve ser no máximo R$ " + pessoa.getValorMaximoEmprestimo());
        }
    }

    private void validaValorEmprestimo() {
        if (valorEmprestimo.compareTo(pessoa.getValorMaximoEmprestimo()) > 0) {
            throw new CustomException(400,  "Valor do empréstimo deve ser no máximo R$ " + pessoa.getValorMaximoEmprestimo());
        }
    }

    public void validateEmprestimo() {
        if (pessoa == null) {
            throw new CustomException(400,  "Pessoa não pode ser nula");
        }
        if (valorEmprestimo == null) {
            throw new CustomException(400,  "Valor do emprestimo não pode ser nulo");
        }
        if (numeroParcelas == null) {
            throw new CustomException(400,  "Número de parcelas não pode ser nulo");
        }
        if (statusPagamento == null) {
            throw new CustomException(400,  "Status de pagamento não pode ser nulo");
        }
        if (dataCriacao == null) {
            throw new CustomException(400, "Data de criação não pode ser nula");
        }
    }
}
