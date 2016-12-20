package com.zheltoukhov.linguaneo.translator.yandex;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:translator.properties")
public class PropertiesLoader {

    @Value("${ya.api_key}")
    private String api_key;

    @Value("${ya.translation_url}")
    private String translatorUrl;

    @Value("${ya.recognition_url}")
    private String recognitionUrl;

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getTranslatorUrl() {
        return translatorUrl;
    }

    public void setTranslatorUrl(String translatorUrl) {
        this.translatorUrl = translatorUrl;
    }

    public String getRecognitionUrl() {
        return recognitionUrl;
    }

    public void setRecognitionUrl(String recognitionUrl) {
        this.recognitionUrl = recognitionUrl;
    }
}
