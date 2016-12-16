package com.zheltoukhov.linguaneo.translator;

public class TranslationDto {
    private String engValue;
    private String rusValue;

    public TranslationDto(String engValue, String rusValue) {
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
