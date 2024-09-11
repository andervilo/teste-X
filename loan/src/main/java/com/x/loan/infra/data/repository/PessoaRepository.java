package com.x.loan.infra.data.repository;

import com.x.loan.infra.data.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<PessoaEntity, Long> {

    Optional<PessoaEntity> findByIdentificador(String identificador);
}
