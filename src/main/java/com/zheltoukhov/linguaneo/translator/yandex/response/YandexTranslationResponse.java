package com.zheltoukhov.linguaneo.translator.yandex.response;

import com.zheltoukhov.linguaneo.translator.TranslationDto;

/**
 * Created by Maksim on 08.12.2016.
 */
public class YandexTranslationResponse {
    private String code;
    private String[] text;
    private String lang;

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

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
