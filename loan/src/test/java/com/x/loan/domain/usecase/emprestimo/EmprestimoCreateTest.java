package com.x.loan.domain.usecase.emprestimo;

import com.x.loan.domain.model.EmprestimoDomain;
import com.x.loan.domain.service.EmprestimoDomainService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmprestimoCreateTest {

    @Test
    void executeWithValidEmprestimo() {
        EmprestimoDomainService emprestimoDomainService = mock(EmprestimoDomainService.class);
        EmprestimoDomain emprestimo = mock(EmprestimoDomain.class);
        when(emprestimoDomainService.create(emprestimo)).thenReturn(emprestimo);

        EmprestimoCreate emprestimoCreate = new EmprestimoCreate(emprestimoDomainService);
        EmprestimoDomain result = emprestimoCreate.execute(emprestimo);

        assertNotNull(result);
        assertEquals(emprestimo, result);
        verify(emprestimoDomainService, times(1)).create(emprestimo);
    }

    @Test
    void executeWithNullEmprestimo() {
        EmprestimoDomainService emprestimoDomainService = mock(EmprestimoDomainService.class);
        when(emprestimoDomainService.create(null)).thenThrow(new IllegalArgumentException("Emprestimo cannot be null"));

        EmprestimoCreate emprestimoCreate = new EmprestimoCreate(emprestimoDomainService);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            emprestimoCreate.execute(null);
        });

        assertEquals("Emprestimo cannot be null", exception.getMessage());
        verify(emprestimoDomainService, times(1)).create(null);
    }

    @Test
    void executeWithInvalidEmprestimo() {
        EmprestimoDomainService emprestimoDomainService = mock(EmprestimoDomainService.class);
        EmprestimoDomain emprestimo = mock(EmprestimoDomain.class);
        when(emprestimoDomainService.create(emprestimo)).thenThrow(new IllegalArgumentException("Invalid Emprestimo"));

        EmprestimoCreate emprestimoCreate = new EmprestimoCreate(emprestimoDomainService);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            emprestimoCreate.execute(emprestimo);
        });

        assertEquals("Invalid Emprestimo", exception.getMessage());
        verify(emprestimoDomainService, times(1)).create(emprestimo);
    }
}