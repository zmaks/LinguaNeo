package com.zheltoukhov.linguaneo.dto.translation;

import com.zheltoukhov.linguaneo.Constants;
import com.zheltoukhov.linguaneo.validation.annotation.ValidText;

import static com.zheltoukhov.linguaneo.Constants.Messages.TEXT_VALIDATION_MESSAGE;
import static com.zheltoukhov.linguaneo.Constants.Messages.WORD_VALIDATION_MESSAGE;

/**
 * Created by Maksim on 12.12.2016.
 */
public class TranslateTextRequestDto {
    @ValidText(message = TEXT_VALIDATION_MESSAGE)
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
