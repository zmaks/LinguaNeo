package com.zheltoukhov.linguaneo.dto;

/**
 * Created by Maksim on 07.12.2016.
 */
public class TranslationWordDto extends WordDto{
    private boolean isExists;

    public TranslationWordDto(String engValue, String rusValue, boolean isExists) {
        super(engValue, rusValue);
        this.isExists = isExists;
    }

    public boolean isExists() {
        return isExists;
    }

    public void setExists(boolean exists) {
        isExists = exists;
    }
}
