package com.x.loan.domain.usecase.pessoa;

import com.x.loan.domain.model.PessoaDomain;
import com.x.loan.domain.service.PessoaDomainService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PessoaFindByIdTest {

    @Test
    void executeWithValidId() {
        PessoaDomainService pessoaDomainService = mock(PessoaDomainService.class);
        PessoaDomain pessoa = new PessoaDomain();
        Long pessoaId = 1L;

        when(pessoaDomainService.findById(pessoaId)).thenReturn(pessoa);

        PessoaFindById pessoaFindById = new PessoaFindById(pessoaDomainService);
        PessoaDomain result = pessoaFindById.execute(pessoaId);

        assertNotNull(result);
        assertEquals(pessoa, result);
        verify(pessoaDomainService, times(1)).findById(pessoaId);
    }

    @Test
    void executeWithNonExistentId() {
        PessoaDomainService pessoaDomainService = mock(PessoaDomainService.class);
        Long pessoaId = 1L;

        when(pessoaDomainService.findById(pessoaId)).thenReturn(null);

        PessoaFindById pessoaFindById = new PessoaFindById(pessoaDomainService);
        PessoaDomain result = pessoaFindById.execute(pessoaId);

        assertNull(result);
        verify(pessoaDomainService, times(1)).findById(pessoaId);
    }
}