package com.x.loan.infra.service;

import com.x.loan.domain.exception.CustomException;
import com.x.loan.domain.model.PessoaDomain;
import com.x.loan.infra.data.entity.PessoaEntity;
import com.x.loan.infra.data.repository.PessoaRepository;
import com.x.loan.infra.mapper.PessoaInfraMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PessoaServiceTest {

    private final PessoaRepository pessoaRepository = mock(PessoaRepository.class);
    private final PessoaInfraMapper pessoaInfraMapper = mock(PessoaInfraMapper.class);
    private final PessoaService pessoaService = new PessoaService(pessoaRepository, pessoaInfraMapper);

    @Test
    void saveWithValidPessoa() {
        PessoaDomain pessoaDomain = new PessoaDomain();
        PessoaEntity pessoaEntity = new PessoaEntity();
        when(pessoaInfraMapper.toEntity(pessoaDomain)).thenReturn(pessoaEntity);
        when(pessoaRepository.save(pessoaEntity)).thenReturn(pessoaEntity);
        when(pessoaInfraMapper.toDomain(pessoaEntity)).thenReturn(pessoaDomain);

        PessoaDomain result = pessoaService.save(pessoaDomain);

        assertNotNull(result);
        assertEquals(pessoaDomain, result);
        verify(pessoaRepository, times(1)).save(pessoaEntity);
    }

    @Test
    void deleteWithValidId() {
        Long id = 1L;
        when(pessoaRepository.existsById(id)).thenReturn(true);

        pessoaService.delete(id);

        verify(pessoaRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteWithNonExistentId() {
        Long id = 1L;
        when(pessoaRepository.existsById(id)).thenReturn(false);

        CustomException exception = assertThrows(CustomException.class, () -> {
            pessoaService.delete(id);
        });

        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatus());
        assertEquals("Pessoa " + id + " não encontrada!", exception.getMessage());
        verify(pessoaRepository, never()).deleteById(id);
    }

    @Test
    void findByIdWithValidId() {
        Long id = 1L;
        PessoaEntity pessoaEntity = new PessoaEntity();
        PessoaDomain pessoaDomain = new PessoaDomain();
        when(pessoaRepository.findById(id)).thenReturn(Optional.of(pessoaEntity));
        when(pessoaInfraMapper.toDomain(pessoaEntity)).thenReturn(pessoaDomain);

        PessoaDomain result = pessoaService.findById(id);

        assertNotNull(result);
        assertEquals(pessoaDomain, result);
        verify(pessoaRepository, times(1)).findById(id);
    }

    @Test
    void findByIdWithNonExistentId() {
        Long id = 1L;
        when(pessoaRepository.findById(id)).thenReturn(Optional.empty());

        CustomException exception = assertThrows(CustomException.class, () -> {
            pessoaService.findById(id);
        });

        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatus());
        assertEquals("Pessoa " + id + " não encontrada!", exception.getMessage());
        verify(pessoaRepository, times(1)).findById(id);
    }

    @Test
    void findAllWithPessoas() {
        List<PessoaEntity> pessoaEntities = List.of(new PessoaEntity(), new PessoaEntity());
        List<PessoaDomain> pessoaDomains = List.of(new PessoaDomain(), new PessoaDomain());
        when(pessoaRepository.findAll()).thenReturn(pessoaEntities);
        when(pessoaInfraMapper.toDomainList(pessoaEntities)).thenReturn(pessoaDomains);

        List<PessoaDomain> result = pessoaService.findAll();

        assertNotNull(result);
        assertEquals(pessoaDomains.size(), result.size());
        assertEquals(pessoaDomains, result);
        verify(pessoaRepository, times(1)).findAll();
    }

    @Test
    void findAllWithNoPessoas() {
        when(pessoaRepository.findAll()).thenReturn(List.of());
        when(pessoaInfraMapper.toDomainList(List.of())).thenReturn(List.of());

        List<PessoaDomain> result = pessoaService.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(pessoaRepository, times(1)).findAll();
    }

    @Test
    void findByIdentificadorWithValidIdentificador() {
        String identificador = "123456";
        PessoaEntity pessoaEntity = new PessoaEntity();
        PessoaDomain pessoaDomain = new PessoaDomain();
        when(pessoaRepository.findByIdentificador(identificador)).thenReturn(Optional.of(pessoaEntity));
        when(pessoaInfraMapper.toDomain(pessoaEntity)).thenReturn(pessoaDomain);

        PessoaDomain result = pessoaService.findByIdentificador(identificador);

        assertNotNull(result);
        assertEquals(pessoaDomain, result);
        verify(pessoaRepository, times(1)).findByIdentificador(identificador);
    }

    @Test
    void findByIdentificadorWithNonExistentIdentificador() {
        String identificador = "123456";
        when(pessoaRepository.findByIdentificador(identificador)).thenReturn(Optional.empty());

        CustomException exception = assertThrows(CustomException.class, () -> {
            pessoaService.findByIdentificador(identificador);
        });

        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatus());
        assertEquals("Pessoa com identificador " + identificador + " não encontrada!", exception.getMessage());
        verify(pessoaRepository, times(1)).findByIdentificador(identificador);
    }
}