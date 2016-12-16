package com.zheltoukhov.linguaneo.dto;

public class ErrorDto {
    private String field;
    private String errorMessage;

    public ErrorDto(String field, String message) {
        this.field = field;
        this.errorMessage = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
