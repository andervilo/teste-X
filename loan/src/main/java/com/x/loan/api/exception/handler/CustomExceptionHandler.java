package com.x.loan.api.exception.handler;

import com.x.loan.api.exception.dto.FriendlyErrorResponse;
import com.x.loan.domain.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException ex) {
        HttpStatus httpStatus = HttpStatus.valueOf(ex.getStatus());
        FriendlyErrorResponse errorResponse = FriendlyErrorResponse.builder()
                .timestamp(ex.getTimestamp().toString())
                .status(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}

