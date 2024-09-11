package com.x.loan.api.exception.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.x.loan.api.exception.dto.FriendlyErrorResponse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ErrorConverter {

    public static FriendlyErrorResponse convertToFriendlyError(String jsonResponse) {
        try {
            String jsonMessage = extractJson(jsonResponse).replace("[", "").replace("]", "");
            ObjectMapper mapper = new ObjectMapper();
            FriendlyErrorResponse errorResponse = mapper.readValue(jsonMessage, FriendlyErrorResponse.class);

            return errorResponse;
        } catch (Exception e) {
            return null;
        }
    }

    public static String extractJson(String input) {
        String regex = "\\[\\{.*\\}\\]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.group(0);
        }

        return null;
    }
}

