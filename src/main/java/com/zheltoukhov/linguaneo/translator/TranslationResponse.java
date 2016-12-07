package com.zheltoukhov.linguaneo.translator;

/**
 * Created by Maksim on 08.12.2016.
 */
public abstract class TranslationResponse {
    private String code;
    private String[] text;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String[] getText() {
        return text;
    }

    public void setText(String[] text) {
        this.text = text;
    }
}
