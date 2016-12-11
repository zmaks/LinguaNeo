package com.zheltoukhov.linguaneo.translator.yandex.response;

/**
 * Created by mazh0416 on 12/8/2016.
 */
public class YandexRecognitionResponse {
    private String code;
    private String lang;

    public YandexRecognitionResponse(){}

    public YandexRecognitionResponse(String code, String lang) {
        this.code = code;
        this.lang = lang;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
