package com.x.loan.infra.mapper;

import com.x.loan.domain.factory.PessoaDomainFactory;
import com.x.loan.domain.model.PessoaDomain;
import com.x.loan.domain.factory.IdentificadorFactory;
import com.x.loan.domain.vo.IdentificadorBaseVO;
import com.x.loan.infra.data.entity.PessoaEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PessoaInfraMapper {
    public PessoaDomain toDomain(PessoaEntity pessoaEntity){
        IdentificadorBaseVO identificador = IdentificadorFactory.create(pessoaEntity.getTipoIdentificador(), pessoaEntity.getIdentificador());
        return PessoaDomainFactory.create(
                pessoaEntity.getId(),
                pessoaEntity.getNome(),
                pessoaEntity.getDataNascimento(),
                identificador,
                pessoaEntity.getValorMinimoMensal(),
                pessoaEntity.getValorMaximoEmprestimo()
                );
    }

    public List<PessoaDomain> toDomainList(List<PessoaEntity> pessoas){
        return pessoas.stream().map(this::toDomain).toList();
    }

    public PessoaEntity toEntity(PessoaDomain pessoaDomain) {
        return PessoaEntity.builder()
                .id(pessoaDomain.getId())
                .nome(pessoaDomain.getNome())
                .dataNascimento(pessoaDomain.getDataNascimento())
                .tipoIdentificador(pessoaDomain.getIdentificador().getTipo())
                .identificador(pessoaDomain.getIdentificador().getIdentificador())
                .valorMaximoEmprestimo(pessoaDomain.getValorMaximoEmprestimo())
                .valorMinimoMensal(pessoaDomain.getValorMinimoMensal())
                .build();
    }
}
