package com.x.loan.api.mapper;

import com.x.loan.api.dto.PessoaCreateRequest;
import com.x.loan.api.dto.PessoaCreateResponse;
import com.x.loan.domain.factory.IdentificadorFactory;
import com.x.loan.domain.factory.PessoaDomainFactory;
import com.x.loan.domain.model.PessoaDomain;
import com.x.loan.domain.vo.IdentificadorBaseVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PessoaRequetResponseMapper {

    public PessoaDomain toDomain(PessoaCreateRequest pessoaCreateRequest) {
        IdentificadorBaseVO identificador = IdentificadorFactory.create(pessoaCreateRequest.getTipoIdentificador(), pessoaCreateRequest.getIdentificador());
        return PessoaDomainFactory.create(pessoaCreateRequest.getNome(), pessoaCreateRequest.getDataNascimento(), identificador);
    }

    public PessoaCreateResponse toResponse(PessoaDomain pessoaDomain) {
        return PessoaCreateResponse.builder()
                .id(pessoaDomain.getId())
                .nome(pessoaDomain.getNome())
                .dataNascimento(pessoaDomain.getDataNascimento())
                .tipoIdentificador(pessoaDomain.getIdentificador().getTipo())
                .identificador(pessoaDomain.getIdentificador().getIdentificador())
                .valorMaximoTotalEmprestimo(pessoaDomain.getValorMaximoEmprestimo())
                .valorMinimoMensalParcela(pessoaDomain.getValorMinimoMensal())
                .build();
    }

    public List<PessoaCreateResponse> toResponseList(List<PessoaDomain> pessoaDomainList) {
        return pessoaDomainList.stream()
                .map(this::toResponse)
                .toList();
    }
}
