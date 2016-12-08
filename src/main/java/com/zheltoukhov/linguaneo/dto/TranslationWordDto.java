package com.zheltoukhov.linguaneo.dto;

/**
 * Created by Maksim on 07.12.2016.
 */
public class TranslationWordDto {
    private String engValue;
    private String rusValue;
    private boolean isExists;

    public TranslationWordDto(String engValue, String rusValue, boolean isExists) {
        this.engValue = engValue;
        this.rusValue = rusValue;
        this.isExists = isExists;
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

    public boolean isExists() {
        return isExists;
    }

    public void setExists(boolean exists) {
        isExists = exists;
    }
}
