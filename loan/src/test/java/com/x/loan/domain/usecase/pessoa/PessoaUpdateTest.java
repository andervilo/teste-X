package com.x.loan.domain.usecase.pessoa;

import com.x.loan.domain.model.PessoaDomain;
import com.x.loan.domain.service.PessoaDomainService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PessoaUpdateTest {

    @Test
    void executeWithValidPessoa() {
        PessoaDomainService pessoaDomainService = mock(PessoaDomainService.class);
        PessoaDomain pessoa = new PessoaDomain();
        when(pessoaDomainService.update(pessoa)).thenReturn(pessoa);

        PessoaUpdate pessoaUpdate = new PessoaUpdate(pessoaDomainService);
        PessoaDomain result = pessoaUpdate.execute(pessoa);

        assertNotNull(result);
        assertEquals(pessoa, result);
        verify(pessoaDomainService, times(1)).update(pessoa);
    }

    @Test
    void executeWithNullPessoa() {
        PessoaDomainService pessoaDomainService = mock(PessoaDomainService.class);
        when(pessoaDomainService.update(null)).thenThrow(new IllegalArgumentException("Pessoa cannot be null"));

        PessoaUpdate pessoaUpdate = new PessoaUpdate(pessoaDomainService);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pessoaUpdate.execute(null);
        });

        assertEquals("Pessoa cannot be null", exception.getMessage());
        verify(pessoaDomainService, times(1)).update(null);
    }

}