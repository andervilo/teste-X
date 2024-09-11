package com.x.loan.api.resource;

import com.x.loan.api.dto.PessoaCreateRequest;
import com.x.loan.api.dto.PessoaCreateResponse;
import com.x.loan.api.mapper.PessoaRequetResponseMapper;
import com.x.loan.domain.model.PessoaDomain;
import com.x.loan.domain.usecase.pessoa.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pessoa")
@RequiredArgsConstructor
@Tag(name = "Pessoa", description = "API de Pessoa")
public class PessoaResource {

    private final PessoaCreate pessoaCreate;
    private final PessoaUpdate pessoaUpdate;
    private final PessoaDelete pessoaDelete;
    private final PessoaFindAll pessoaFindAll;
    private final PessoaFindById pessoaFindById;
    private final PessoaRequetResponseMapper pessoaRequetResponseMapper;


    @GetMapping()
    @Operation(summary = "Buscar todas as pessoas")
    public ResponseEntity<List<PessoaCreateResponse>> findAll() {
        List<PessoaDomain> pessoaDomainList = pessoaFindAll.execute();
        return ResponseEntity.ok(pessoaRequetResponseMapper.toResponseList(pessoaDomainList));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar uma pessoa pelo id")
    public ResponseEntity<PessoaCreateResponse> findById(@PathVariable Long id) {
        PessoaDomain pessoaDomain = pessoaFindById.execute(id);
        return ResponseEntity.ok(pessoaRequetResponseMapper.toResponse(pessoaDomain));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar uma pessoa")
    public ResponseEntity<PessoaCreateResponse> update(@PathVariable Long id, @RequestBody @Valid PessoaCreateRequest pessoaCreateRequest) {
        PessoaDomain pessoaDomain = pessoaRequetResponseMapper.toDomain(pessoaCreateRequest);
        pessoaDomain.setId(id);
        PessoaDomain pessoaDomainUpdated = pessoaUpdate.execute(pessoaDomain);
        return ResponseEntity.ok(pessoaRequetResponseMapper.toResponse(pessoaDomainUpdated));
    }

    @PostMapping()
    @Operation(summary = "Criar uma pessoa")
    public ResponseEntity<PessoaCreateResponse> create(@RequestBody @Valid PessoaCreateRequest pessoaCreateRequest) {
        PessoaDomain pessoaDomain = pessoaRequetResponseMapper.toDomain(pessoaCreateRequest);
        PessoaDomain pessoaDomainCreated = pessoaCreate.execute(pessoaDomain);
        return ResponseEntity.ok(pessoaRequetResponseMapper.toResponse(pessoaDomainCreated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar uma pessoa")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        pessoaDelete.execute(id);
        return ResponseEntity.noContent().build();
    }
}
