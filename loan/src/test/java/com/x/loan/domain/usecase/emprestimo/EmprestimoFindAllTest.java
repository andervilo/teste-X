package com.x.loan.domain.usecase.emprestimo;

import com.x.loan.domain.model.EmprestimoDomain;
import com.x.loan.domain.service.EmprestimoDomainService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

class EmprestimoFindAllTest {

    @Test
    void executeWithEmprestimos() {
        EmprestimoDomainService emprestimoDomainService = mock(EmprestimoDomainService.class);
        EmprestimoDomain emp1 = mock(EmprestimoDomain.class);
        EmprestimoDomain emp2 = mock(EmprestimoDomain.class);
        List<EmprestimoDomain> emprestimos = List.of(emp1, emp2);
        when(emprestimoDomainService.findAll()).thenReturn(emprestimos);

        EmprestimoFindAll emprestimoFindAll = new EmprestimoFindAll(emprestimoDomainService);
        List<EmprestimoDomain> result = emprestimoFindAll.execute();

        assertNotNull(result);
        assertEquals(emprestimos.size(), result.size());
        assertEquals(emprestimos, result);
        verify(emprestimoDomainService, times(1)).findAll();
    }

    @Test
    void executeWithNoEmprestimos() {
        EmprestimoDomainService emprestimoDomainService = mock(EmprestimoDomainService.class);
        when(emprestimoDomainService.findAll()).thenReturn(Collections.emptyList());

        EmprestimoFindAll emprestimoFindAll = new EmprestimoFindAll(emprestimoDomainService);
        List<EmprestimoDomain> result = emprestimoFindAll.execute();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(emprestimoDomainService, times(1)).findAll();
    }

    @Test
    void executeWithNullResult() {
        EmprestimoDomainService emprestimoDomainService = mock(EmprestimoDomainService.class);
        when(emprestimoDomainService.findAll()).thenReturn(null);

        EmprestimoFindAll emprestimoFindAll = new EmprestimoFindAll(emprestimoDomainService);
        List<EmprestimoDomain> result = emprestimoFindAll.execute();

        assertNull(result);
        verify(emprestimoDomainService, times(1)).findAll();
    }
}