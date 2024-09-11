package com.x.loan.domain.usecase.emprestimo;

import com.x.loan.domain.model.EmprestimoDomain;
import com.x.loan.domain.model.PessoaDomain;
import com.x.loan.domain.service.EmprestimoDomainService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

class EmprestimoFindByPessoaTest {

    @Test
    void executeWithValidPessoa() {
        EmprestimoDomainService emprestimoDomainService = mock(EmprestimoDomainService.class);
        PessoaDomain pessoa = mock(PessoaDomain.class);
        EmprestimoDomain emp1 = mock(EmprestimoDomain.class);
        EmprestimoDomain emp2 = mock(EmprestimoDomain.class);
        List<EmprestimoDomain> emprestimos = List.of(emp1, emp2);
        when(emprestimoDomainService.findByPessoa(pessoa)).thenReturn(emprestimos);

        EmprestimoFindByPessoa emprestimoFindByPessoa = new EmprestimoFindByPessoa(emprestimoDomainService);
        List<EmprestimoDomain> result = emprestimoFindByPessoa.execute(pessoa);

        assertNotNull(result);
        assertEquals(emprestimos.size(), result.size());
        assertEquals(emprestimos, result);
        verify(emprestimoDomainService, times(1)).findByPessoa(pessoa);
    }

    @Test
    void executeWithNoEmprestimos() {
        EmprestimoDomainService emprestimoDomainService = mock(EmprestimoDomainService.class);
        PessoaDomain pessoa = mock(PessoaDomain.class);
        when(emprestimoDomainService.findByPessoa(pessoa)).thenReturn(Collections.emptyList());

        EmprestimoFindByPessoa emprestimoFindByPessoa = new EmprestimoFindByPessoa(emprestimoDomainService);
        List<EmprestimoDomain> result = emprestimoFindByPessoa.execute(pessoa);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(emprestimoDomainService, times(1)).findByPessoa(pessoa);
    }

}