package com.x.loan.infra.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "paymentServiceClient",
        url = "${feign.client.config.paymentServiceClient.host}")
public interface PaymentServiceClient {

    @PostMapping("/api/v1/emprestimo/payment/{id}")
    ResponseEntity<String> pay(@PathVariable("id") Long id);
}
