package com.zheltoukhov.linguaneo.dto.translation;

import com.zheltoukhov.linguaneo.validation.annotation.ValidText;

import static com.zheltoukhov.linguaneo.Constants.Messages.TEXT_VALIDATION_MESSAGE;

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
