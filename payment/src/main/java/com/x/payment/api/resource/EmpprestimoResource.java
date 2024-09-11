package com.x.payment.api.resource;

import com.x.payment.data.entity.EmprestimoEntity;
import com.x.payment.service.EmprestimoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/emprestimo/payment")
@RequiredArgsConstructor
public class EmpprestimoResource {

    private final EmprestimoService emprestimoService;

    @PostMapping("/{id}")
    public ResponseEntity<?> pay(@PathVariable Long id){
        emprestimoService.pay(id);
        return ResponseEntity.accepted().body("Pagamento efetuado com sucesso!");
    }
}
