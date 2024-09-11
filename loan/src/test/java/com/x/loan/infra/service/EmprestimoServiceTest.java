package com.x.loan.infra.service;

import com.x.loan.domain.exception.CustomException;
import com.x.loan.domain.model.EmprestimoDomain;
import com.x.loan.domain.model.PessoaDomain;
import com.x.loan.infra.data.entity.EmprestimoEntity;
import com.x.loan.infra.data.repository.EmprestimoRepository;
import com.x.loan.infra.mapper.EmprestimoInfraMapper;
import com.x.loan.infra.client.PaymentServiceClient;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmprestimoServiceTest {

    private final EmprestimoRepository emprestimoRepository = mock(EmprestimoRepository.class);
    private final EmprestimoInfraMapper emprestimoInfraMapper = mock(EmprestimoInfraMapper.class);
    private final PaymentServiceClient paymentServiceClient = mock(PaymentServiceClient.class);
    private final EmprestimoService emprestimoService = new EmprestimoService(emprestimoRepository, emprestimoInfraMapper, paymentServiceClient);

    @Test
    void findAllWithEmprestimos() {
        EmprestimoDomain emp1 = mock(EmprestimoDomain.class);
        EmprestimoDomain emp2 = mock(EmprestimoDomain.class);
        List<EmprestimoDomain> emprestimos = List.of(emp1, emp2);

        EmprestimoEntity empE1 = mock(EmprestimoEntity.class);
        EmprestimoEntity empE2 = mock(EmprestimoEntity.class);
        List<EmprestimoEntity> emprestimosE = List.of(empE1, empE2);
        when(emprestimoRepository.findAll()).thenReturn(emprestimosE);
        when(emprestimoInfraMapper.toDomainList(anyList())).thenReturn(emprestimos);

        List<EmprestimoDomain> result = emprestimoService.findAll();

        assertNotNull(result);
        assertEquals(emprestimos.size(), result.size());
        assertEquals(emprestimos, result);
        verify(emprestimoRepository, times(1)).findAll();
    }

    @Test
    void findAllWithNoEmprestimos() {
        when(emprestimoRepository.findAll()).thenReturn(List.of());
        when(emprestimoInfraMapper.toDomainList(List.of())).thenReturn(List.of());

        List<EmprestimoDomain> result = emprestimoService.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(emprestimoRepository, times(1)).findAll();
    }

    @Test
    void findByPessoaWithValidPessoa() {
        PessoaDomain pessoa = new PessoaDomain();
        pessoa.setId(1L);
        EmprestimoDomain emp1 = mock(EmprestimoDomain.class);
        EmprestimoDomain emp2 = mock(EmprestimoDomain.class);
        List<EmprestimoDomain> emprestimos = List.of(emp1, emp2);

        EmprestimoEntity empE1 = mock(EmprestimoEntity.class);
        EmprestimoEntity empE2 = mock(EmprestimoEntity.class);
        List<EmprestimoEntity> emprestimosE = List.of(empE1, empE2);
        when(emprestimoRepository.findAllByPessoaId(pessoa.getId())).thenReturn(emprestimosE);
        when(emprestimoInfraMapper.toDomainList(anyList())).thenReturn(emprestimos);

        List<EmprestimoDomain> result = emprestimoService.findByPessoa(pessoa);

        assertNotNull(result);
        assertEquals(emprestimos.size(), result.size());
        assertEquals(emprestimos, result);
        verify(emprestimoRepository, times(1)).findAllByPessoaId(pessoa.getId());
    }

    @Test
    void findByPessoaWithNoEmprestimos() {
        PessoaDomain pessoa = new PessoaDomain();
        pessoa.setId(1L);
        when(emprestimoRepository.findAllByPessoaId(pessoa.getId())).thenReturn(List.of());
        when(emprestimoInfraMapper.toDomainList(List.of())).thenReturn(List.of());

        List<EmprestimoDomain> result = emprestimoService.findByPessoa(pessoa);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(emprestimoRepository, times(1)).findAllByPessoaId(pessoa.getId());
    }

    @Test
    void findByIdWithValidId() {
        Long id = 1L;

        EmprestimoDomain emp1 = mock(EmprestimoDomain.class);
        EmprestimoEntity empE1 = mock(EmprestimoEntity.class);
        when(emprestimoRepository.findById(id)).thenReturn(Optional.of(empE1));
        when(emprestimoInfraMapper.toDomain(any())).thenReturn(emp1);

        EmprestimoDomain result = emprestimoService.findById(id);

        assertNotNull(result);
        assertEquals(emp1, result);
        verify(emprestimoRepository, times(1)).findById(id);
    }

    @Test
    void findByIdWithNonExistentId() {
        Long id = 1L;
        when(emprestimoRepository.findById(id)).thenReturn(Optional.empty());

        CustomException exception = assertThrows(CustomException.class, () -> {
            emprestimoService.findById(id);
        });

        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatus());
        assertEquals("Empréstimo " + id + " não encontrado!", exception.getMessage());
        verify(emprestimoRepository, times(1)).findById(id);
    }

    @Test
    void payWithValidId() {
        Long id = 1L;
        when(paymentServiceClient.pay(id)).thenReturn(new ResponseEntity<>("Payment successful", HttpStatus.OK));

        Boolean result = emprestimoService.pay(id);

        assertNotNull(result);
        assertTrue(result);
        verify(paymentServiceClient, times(1)).pay(id);
    }

    @Test
    void payWithInvalidId() {
        Long id = 1L;
        when(paymentServiceClient.pay(id)).thenReturn(new ResponseEntity<>("Payment failed", HttpStatus.BAD_REQUEST));

        Boolean result = emprestimoService.pay(id);

        assertNull(result);
        verify(paymentServiceClient, times(1)).pay(id);
    }
}