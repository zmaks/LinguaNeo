package com.zheltoukhov.linguaneo.dto;

/**
 * Created by mazh0416 on 12/12/2016.
 */
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
