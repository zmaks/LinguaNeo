package com.zheltoukhov.linguaneo.dto.translation;

import com.zheltoukhov.linguaneo.dto.word.WordDto;

public class TranslationWordDto extends WordDto {
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

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

}
