package com.x.loan.api.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendlyErrorResponse {
    private String timestamp;
    private int status;
    private String error;
    private Object message;

}

