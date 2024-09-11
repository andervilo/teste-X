package com.x.loan.domain.usecase.pessoa;

import com.x.loan.domain.model.PessoaDomain;
import com.x.loan.domain.service.PessoaDomainService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PessoaCreateTest {

    @Test
    void executeWithValidPessoa() {
        PessoaDomainService pessoaDomainService = mock(PessoaDomainService.class);
        PessoaDomain pessoa = new PessoaDomain();
        when(pessoaDomainService.save(pessoa)).thenReturn(pessoa);

        PessoaCreate pessoaCreate = new PessoaCreate(pessoaDomainService);
        PessoaDomain result = pessoaCreate.execute(pessoa);

        assertNotNull(result);
        assertEquals(pessoa, result);
        verify(pessoaDomainService, times(1)).save(pessoa);
    }

    @Test
    void executeWithNullPessoa() {
        PessoaDomainService pessoaDomainService = mock(PessoaDomainService.class);
        when(pessoaDomainService.save(null)).thenThrow(new IllegalArgumentException("Pessoa cannot be null"));

        PessoaCreate pessoaCreate = new PessoaCreate(pessoaDomainService);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pessoaCreate.execute(null);
        });

        assertEquals("Pessoa cannot be null", exception.getMessage());
        verify(pessoaDomainService, times(1)).save(null);
    }
}