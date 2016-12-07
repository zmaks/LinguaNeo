package com.zheltoukhov.linguaneo.translator.yandex;

import com.zheltoukhov.linguaneo.translator.TranslationResponse;

/**
 * Created by Maksim on 08.12.2016.
 */
public class YandexTranslationResponse extends TranslationResponse {
    private String lang;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
