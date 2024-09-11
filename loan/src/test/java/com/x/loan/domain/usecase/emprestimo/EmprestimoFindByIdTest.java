package com.x.loan.domain.usecase.emprestimo;

import com.x.loan.domain.model.EmprestimoDomain;
import com.x.loan.domain.service.EmprestimoDomainService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmprestimoFindByIdTest {

    @Test
    void executeWithValidId() {
        EmprestimoDomainService emprestimoDomainService = mock(EmprestimoDomainService.class);
        EmprestimoDomain emprestimo = mock(EmprestimoDomain.class);
        Long id = 1L;

        when(emprestimoDomainService.findById(id)).thenReturn(emprestimo);

        EmprestimoFindById emprestimoFindById = new EmprestimoFindById(emprestimoDomainService);
        EmprestimoDomain result = emprestimoFindById.execute(id);

        assertNotNull(result);
        assertEquals(emprestimo, result);
        verify(emprestimoDomainService, times(1)).findById(id);
    }

    @Test
    void executeWithNonExistentId() {
        EmprestimoDomainService emprestimoDomainService = mock(EmprestimoDomainService.class);
        Long id = 1L;

        when(emprestimoDomainService.findById(id)).thenReturn(null);

        EmprestimoFindById emprestimoFindById = new EmprestimoFindById(emprestimoDomainService);
        EmprestimoDomain result = emprestimoFindById.execute(id);

        assertNull(result);
        verify(emprestimoDomainService, times(1)).findById(id);
    }
}