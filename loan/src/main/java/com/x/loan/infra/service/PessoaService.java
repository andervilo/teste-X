package com.x.loan.infra.service;

import com.x.loan.domain.exception.CustomException;
import com.x.loan.domain.model.PessoaDomain;
import com.x.loan.domain.service.PessoaDomainService;
import com.x.loan.infra.data.entity.PessoaEntity;
import com.x.loan.infra.data.repository.PessoaRepository;
import com.x.loan.infra.mapper.PessoaInfraMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaService implements PessoaDomainService {

    private final PessoaRepository pessoaRepository;
    private final PessoaInfraMapper pessoaInfraMapper;

    @Override
    public PessoaDomain save(PessoaDomain pessoa) {
        PessoaEntity pessoaToSave = pessoaInfraMapper.toEntity(pessoa);
        return pessoaInfraMapper.toDomain(pessoaRepository.save(pessoaToSave));
    }

    @Override
    public PessoaDomain update(PessoaDomain pessoa) {
        PessoaEntity pessoaToUpdate = pessoaInfraMapper.toEntity(pessoa);
        PessoaEntity pessoaInDatabase = pessoaInfraMapper.toEntity(findById(pessoa.getId()));
        BeanUtils.copyProperties(pessoaToUpdate, pessoaInDatabase, "id");
        return pessoaInfraMapper.toDomain(pessoaRepository.save(pessoaInDatabase));
    }

    @Override
    public void delete(Long id) {
        if(!pessoaRepository.existsById(id)){
            throw getNotFoundException(id);
        }
        pessoaRepository.deleteById(id);
    }

    private static CustomException getNotFoundException(Long id) {
        return new CustomException(HttpStatus.NOT_FOUND.value(), "Pessoa " + id + " não encontrada!");
    }

    @Override
    public PessoaDomain findById(Long id) {
        return pessoaInfraMapper.toDomain(pessoaRepository.findById(id).orElseThrow(()-> getNotFoundException(id)));
    }

    @Override
    public List<PessoaDomain> findAll() {
        return pessoaInfraMapper.toDomainList(pessoaRepository.findAll());
    }

    @Override
    public PessoaDomain findByIdentificador(String identificador) {
        return pessoaInfraMapper.toDomain(pessoaRepository.findByIdentificador(identificador).orElseThrow(()-> new CustomException(HttpStatus.NOT_FOUND.value(),"Pessoa com identificador "+identificador+" não encontrada!")));
    }
}
