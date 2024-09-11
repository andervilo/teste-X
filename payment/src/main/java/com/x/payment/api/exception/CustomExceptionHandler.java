package com.x.payment.api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", ex.getTimestamp());
        body.put("status", ex.getStatusCode());
        body.put("error", ex.getError());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, ex.getStatus());
    }
}

