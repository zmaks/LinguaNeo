package com.zheltoukhov.linguaneo.dto.translation;

import com.zheltoukhov.linguaneo.Constants;
import com.zheltoukhov.linguaneo.validation.annotation.ValidWord;

import static com.zheltoukhov.linguaneo.Constants.Messages.WORD_VALIDATION_MESSAGE;

/**
 * Created by Maksim on 12.12.2016.
 */
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
