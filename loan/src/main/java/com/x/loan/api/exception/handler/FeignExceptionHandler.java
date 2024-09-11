package com.x.loan.api.exception.handler;

import com.x.loan.api.exception.converter.ErrorConverter;
import com.x.loan.api.exception.dto.FriendlyErrorResponse;
import feign.FeignException;
import feign.RetryableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class FeignExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Object> handleFeignException(FeignException ex) {
        if(ex instanceof RetryableException) {
            FriendlyErrorResponse errorResponse = FriendlyErrorResponse.builder()
                    .error(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase())
                    .message("Serviço temporariamente indisponível")
                    .timestamp(LocalDateTime.now().toString())
                    .status(HttpStatus.SERVICE_UNAVAILABLE.value())
                    .build();
            return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
        }
        FriendlyErrorResponse errorResponse = ErrorConverter.convertToFriendlyError(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.status()));
    }

}

