package com.zheltoukhov.linguaneo.dto;

/**
 * Created by mazh0416 on 12/12/2016.
 */
public class ErrorDto {
    private String field;
    private String message;

    public ErrorDto(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
