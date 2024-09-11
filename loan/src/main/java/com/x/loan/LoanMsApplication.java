package com.x.loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LoanMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanMsApplication.class, args);
	}

}
