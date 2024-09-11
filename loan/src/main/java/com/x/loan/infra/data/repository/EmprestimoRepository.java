package com.x.loan.infra.data.repository;

import com.x.loan.infra.data.entity.EmprestimoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<EmprestimoEntity, Long> {
    List<EmprestimoEntity> findAllByPessoaId(Long pessoaId);
}
