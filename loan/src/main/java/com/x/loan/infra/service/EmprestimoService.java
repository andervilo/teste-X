package com.x.loan.infra.service;

import com.x.loan.domain.exception.CustomException;
import com.x.loan.domain.model.EmprestimoDomain;
import com.x.loan.domain.model.PessoaDomain;
import com.x.loan.domain.service.EmprestimoDomainService;
import com.x.loan.infra.client.PaymentServiceClient;
import com.x.loan.infra.data.repository.EmprestimoRepository;
import com.x.loan.infra.mapper.EmprestimoInfraMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmprestimoService implements EmprestimoDomainService {

    private final EmprestimoRepository emprestimoRepository;
    private final EmprestimoInfraMapper emprestimoInfraMapper;
    private final PaymentServiceClient paymentServiceClient;

    @Override
    public List<EmprestimoDomain> findAll() {
        return emprestimoInfraMapper.toDomainList(emprestimoRepository.findAll());
    }

    @Override
    public List<EmprestimoDomain> findByPessoa(PessoaDomain pessoaDomain) {
        return emprestimoInfraMapper.toDomainList(emprestimoRepository.findAllByPessoaId(pessoaDomain.getId()));
    }

    @Override
    public EmprestimoDomain findById(Long id) {
        return emprestimoInfraMapper.toDomain(emprestimoRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND.value(),"Empréstimo "+id+" não encontrado!")));
    }

    @Override
    public EmprestimoDomain create(EmprestimoDomain emprestimoDomain) {
        return emprestimoInfraMapper.toDomain(emprestimoRepository.save(emprestimoInfraMapper.toEntity(emprestimoDomain)));
    }

    @Override
    public Boolean pay(Long id) {
        ResponseEntity<String> response;
        response = paymentServiceClient.pay(id);
        if (response.getStatusCode().is2xxSuccessful()) {
            return true;
        }
        return null;
    }
}
