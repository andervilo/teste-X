package com.x.loan.domain.usecase.pessoa;

import com.x.loan.domain.model.PessoaDomain;
import com.x.loan.domain.service.PessoaDomainService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PessoaFindByIdentificadorTest {

    @Test
    void executeWithValidIdentificador() {
        PessoaDomainService pessoaDomainService = mock(PessoaDomainService.class);
        PessoaDomain pessoa = new PessoaDomain();
        String identificador = "12345";

        when(pessoaDomainService.findByIdentificador(identificador)).thenReturn(pessoa);

        PessoaFindByIdentificador pessoaFindByIdentificador = new PessoaFindByIdentificador(pessoaDomainService);
        PessoaDomain result = pessoaFindByIdentificador.execute(identificador);

        assertNotNull(result);
        assertEquals(pessoa, result);
        verify(pessoaDomainService, times(1)).findByIdentificador(identificador);
    }

    @Test
    void executeWithNonExistentIdentificador() {
        PessoaDomainService pessoaDomainService = mock(PessoaDomainService.class);
        String identificador = "12345";

        when(pessoaDomainService.findByIdentificador(identificador)).thenReturn(null);

        PessoaFindByIdentificador pessoaFindByIdentificador = new PessoaFindByIdentificador(pessoaDomainService);
        PessoaDomain result = pessoaFindByIdentificador.execute(identificador);

        assertNull(result);
        verify(pessoaDomainService, times(1)).findByIdentificador(identificador);
    }

}