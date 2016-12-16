package com.zheltoukhov.linguaneo.dto.translation;

import com.zheltoukhov.linguaneo.validation.annotation.ValidWord;

import static com.zheltoukhov.linguaneo.Constants.Messages.WORD_VALIDATION_MESSAGE;

public class TranslateWordRequestDto {
    @ValidWord(message = WORD_VALIDATION_MESSAGE)
    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
