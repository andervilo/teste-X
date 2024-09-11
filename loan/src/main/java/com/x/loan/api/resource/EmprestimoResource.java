package com.x.loan.api.resource;

import com.x.loan.api.dto.EmprestimoRequest;
import com.x.loan.api.dto.EmprestimoResponse;
import com.x.loan.api.mapper.EmprestimoRequestResponseMapper;
import com.x.loan.domain.enumeration.TipoReajusteType;
import com.x.loan.domain.model.EmprestimoDomain;
import com.x.loan.domain.model.PessoaDomain;
import com.x.loan.domain.usecase.emprestimo.EmprestimoCreate;
import com.x.loan.domain.usecase.emprestimo.EmprestimoFindAll;
import com.x.loan.domain.usecase.emprestimo.EmprestimoFindById;
import com.x.loan.domain.usecase.emprestimo.EmprestimoPay;
import com.x.loan.domain.usecase.pessoa.PessoaFindByIdentificador;
import com.x.loan.domain.usecase.pessoa.PessoaUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/emprestimo")
@RequiredArgsConstructor
@Tag(name = "Emprestimo", description = "API de Emprestimo")
public class EmprestimoResource {

    private final EmprestimoCreate emprestimoCreate;
    private final EmprestimoPay emprestimoPay;
    private final EmprestimoFindById emprestimoFindById;
    private final EmprestimoFindAll emprestimoFindAll;
    private final PessoaFindByIdentificador pessoaFindByIdentificador;
    private final EmprestimoRequestResponseMapper emprestimoRequestResponseMapper;
    private final PessoaUpdate pessoaUpdate;

    @PostMapping()
    @Operation(summary = "Criar um emprestimo")
    public ResponseEntity<EmprestimoResponse> create(@RequestBody @Valid EmprestimoRequest emprestimo ){
        PessoaDomain pessoaDomain = pessoaFindByIdentificador.execute(emprestimo.getIdentificador());
        EmprestimoDomain emprestimoDomain = emprestimoRequestResponseMapper.toDomain(emprestimo, pessoaDomain);
        EmprestimoDomain emprestimoDomainSaved = emprestimoCreate.execute(emprestimoDomain);
        PessoaDomain pessoaDomainSaved = emprestimoDomainSaved.getPessoa();
        pessoaDomainSaved.reajustarValorMaximoEmprestimo(emprestimoDomainSaved.getValorEmprestimo(), TipoReajusteType.DIMINUIR);
        pessoaUpdate.execute(pessoaDomainSaved);
        return ResponseEntity.ok(emprestimoRequestResponseMapper.toResponse(emprestimoDomainSaved));
    }

    @PostMapping("/payment/{id}")
    @Operation(summary = "Quitar um emprestimo")
    public ResponseEntity<?> pay(@PathVariable Long id){
        emprestimoPay.execute(id);
        return ResponseEntity.accepted().body("Pagamento efetuado com sucesso!");
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar um emprestimo por id")
    public ResponseEntity<EmprestimoResponse> findById(@PathVariable Long id){
        EmprestimoDomain emprestimoDomain = emprestimoFindById.execute(id);
        return ResponseEntity.ok(emprestimoRequestResponseMapper.toResponse(emprestimoDomain));
    }

    @GetMapping()
    @Operation(summary = "Buscar todos os emprestimos")
    public ResponseEntity<List<EmprestimoResponse>> findAll(){
        return ResponseEntity.ok(emprestimoRequestResponseMapper.toResponseList(emprestimoFindAll.execute()));
    }
}
