package com.springboot.restful_web_services.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ErrorDetails {
    private LocalDateTime errorTimeStamp;
    private String message;
    private String errorDetails;

    public ErrorDetails(LocalDateTime errorTimeStamp, String message, String errorDetails) {
        this.errorTimeStamp = errorTimeStamp;
        this.message = message;
        this.errorDetails = errorDetails;
    }

    @Override
    public String toString() {
        return "ErrorDetails{" +
                "errorTimeStamp=" + errorTimeStamp +
                ", message='" + message + '\'' +
                ", errorDetails='" + errorDetails + '\'' +
                '}';
    }

    public LocalDateTime getErrorTimeStamp() {
        return errorTimeStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorDetails() {
        return errorDetails;
    }
}
