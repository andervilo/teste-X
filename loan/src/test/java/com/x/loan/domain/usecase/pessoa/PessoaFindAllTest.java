package com.x.loan.domain.usecase.pessoa;

import com.x.loan.domain.model.PessoaDomain;
import com.x.loan.domain.service.PessoaDomainService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

class PessoaFindAllTest {

    @Test
    void executeWithNonEmptyList() {
        PessoaDomainService pessoaDomainService = mock(PessoaDomainService.class);
        List<PessoaDomain> pessoas = List.of(new PessoaDomain(), new PessoaDomain());
        when(pessoaDomainService.findAll()).thenReturn(pessoas);

        PessoaFindAll pessoaFindAll = new PessoaFindAll(pessoaDomainService);
        List<PessoaDomain> result = pessoaFindAll.execute();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(pessoaDomainService, times(1)).findAll();
    }

    @Test
    void executeWithEmptyList() {
        PessoaDomainService pessoaDomainService = mock(PessoaDomainService.class);
        when(pessoaDomainService.findAll()).thenReturn(Collections.emptyList());

        PessoaFindAll pessoaFindAll = new PessoaFindAll(pessoaDomainService);
        List<PessoaDomain> result = pessoaFindAll.execute();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(pessoaDomainService, times(1)).findAll();
    }
}