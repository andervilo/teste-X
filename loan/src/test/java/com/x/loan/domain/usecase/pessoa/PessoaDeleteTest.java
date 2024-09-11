package com.x.loan.domain.usecase.pessoa;

import com.x.loan.domain.exception.CustomException;
import com.x.loan.domain.model.EmprestimoDomain;
import com.x.loan.domain.model.PessoaDomain;
import com.x.loan.domain.service.PessoaDomainService;
import com.x.loan.domain.usecase.emprestimo.EmprestimoFindByPessoa;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PessoaDeleteTest {

    @Test
    void executeWithNoActiveLoans() {
        PessoaDomainService pessoaDomainService = mock(PessoaDomainService.class);
        EmprestimoFindByPessoa emprestimoFindByPessoa = mock(EmprestimoFindByPessoa.class);
        PessoaDomain pessoa = new PessoaDomain();
        Long pessoaId = 1L;

        when(pessoaDomainService.findById(pessoaId)).thenReturn(pessoa);
        when(emprestimoFindByPessoa.execute(pessoa)).thenReturn(Collections.emptyList());

        PessoaDelete pessoaDelete = new PessoaDelete(pessoaDomainService, emprestimoFindByPessoa);
        pessoaDelete.execute(pessoaId);

        verify(pessoaDomainService, times(1)).delete(pessoaId);
    }

    @Test
    void executeWithActiveLoans() {
        PessoaDomainService pessoaDomainService = mock(PessoaDomainService.class);
        EmprestimoFindByPessoa emprestimoFindByPessoa = mock(EmprestimoFindByPessoa.class);
        EmprestimoDomain emprestimo = mock(EmprestimoDomain.class);
        PessoaDomain pessoa = new PessoaDomain();
        Long pessoaId = 1L;
        List<EmprestimoDomain> emprestimos = List.of(emprestimo);

        when(pessoaDomainService.findById(pessoaId)).thenReturn(pessoa);
        when(emprestimoFindByPessoa.execute(pessoa)).thenReturn(emprestimos);

        PessoaDelete pessoaDelete = new PessoaDelete(pessoaDomainService, emprestimoFindByPessoa);

        CustomException exception = assertThrows(CustomException.class, () -> {
            pessoaDelete.execute(pessoaId);
        });

        assertEquals("Pessoa possui empréstimos ativos!", exception.getMessage());
        verify(pessoaDomainService, never()).delete(pessoaId);
    }
}