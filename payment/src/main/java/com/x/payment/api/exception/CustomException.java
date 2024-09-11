package com.x.payment.api.exception;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

public class CustomException extends RuntimeException {
    private final HttpStatus status;
    private final String message;

    public CustomException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return message;
    }

    public String getError() {
        return status.name();
    }

    public int getStatusCode() {
        return status.value();
    }

    public LocalDateTime getTimestamp() {
        return LocalDateTime.now();
    }
}

