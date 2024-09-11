package com.x.loan.domain.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class CustomException extends RuntimeException {
    private final int status;
    private final String message;

    public CustomException(int status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return LocalDateTime.now();
    }
}

