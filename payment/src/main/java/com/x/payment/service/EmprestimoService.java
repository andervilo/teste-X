package com.x.payment.service;

import com.x.payment.api.exception.CustomException;
import com.x.payment.data.entity.EmprestimoEntity;
import com.x.payment.data.enumeration.StatusPagamentoType;
import com.x.payment.data.repository.EmprestimoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;

    public EmprestimoEntity pay(Long id) {

        EmprestimoEntity emprestimoEntity = emprestimoRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "PAYMENT SERVICE: Empréstimo não encontrado!"));
        if(emprestimoEntity.getStatusPgamento().equals(StatusPagamentoType.PAGO)){
            throw new CustomException(HttpStatus.BAD_REQUEST,"PAYMENT SERVICE: Empréstimo "+id+" á foi pago!");
        }
        emprestimoEntity.setStatusPgamento(StatusPagamentoType.PAGO);
        return emprestimoRepository.save(emprestimoEntity);
    }

}
