package com.zheltoukhov.linguaneo.dto;

/**
 * Created by Maksim on 07.12.2016.
 */
public class TranslationWordDto {
    private String engValue;
    private String rusValue;

    public TranslationWordDto(String engValue, String rusValue) {
        this.engValue = engValue;
        this.rusValue = rusValue;
    }

    public String getEngValue() {
        return engValue;
    }

    public void setEngValue(String engValue) {
        this.engValue = engValue;
    }

    public String getRusValue() {
        return rusValue;
    }

    public void setRusValue(String rusValue) {
        this.rusValue = rusValue;
    }
}
