package com.zheltoukhov.linguaneo.translator.yandex;

import com.zheltoukhov.linguaneo.Constants;
import com.zheltoukhov.linguaneo.translator.Language;
import com.zheltoukhov.linguaneo.translator.TranslationDto;
import com.zheltoukhov.linguaneo.translator.TranslationService;
import com.zheltoukhov.linguaneo.translator.exception.TranslatorException;
import com.zheltoukhov.linguaneo.translator.yandex.response.YandexRecognitionResponse;
import com.zheltoukhov.linguaneo.translator.yandex.response.YandexTranslationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Service
public class YandexTranslationServiceImpl implements TranslationService {

    private String apiKey;
    private String translationUrl;
    private String langRecognitionUrl;

    private static final String RECOGNITION_ERROR_MESSAGE = "Ошибка распознания языка";
    private static final String TRANSLATION_ERROR_MESSAGE = "Ошибка во время перевода";
    private RestTemplate restTemplate;

    @Autowired
    private Constants constants;

    @PostConstruct
    void init(){
        restTemplate = new RestTemplate();
        apiKey = constants.getApiKey();
        translationUrl = constants.getTranslationUrl();
        langRecognitionUrl = constants.getLangRecognitionUrl();
    }

    @Override
    public TranslationDto translateEngToRus(String word) throws TranslatorException{
        YandexTranslationResponse response = translateWord(word, Language.ENG, Language.RUS);
        if (response == null)
            throw new TranslatorException(TRANSLATION_ERROR_MESSAGE);
        return new TranslationDto(word, response.getText()[0]);
    }

    @Override
    public TranslationDto translateRusToEng(String word) throws TranslatorException{
        YandexTranslationResponse response = translateWord(word, Language.RUS, Language.ENG);
        return new TranslationDto(response.getText()[0], word);
    }

    @Override
    public TranslationDto translateWord(String word) throws TranslatorException {
        Language source = recognizeLanguage(word);
        if (source==null)
            throw new TranslatorException(RECOGNITION_ERROR_MESSAGE);
        if (source.equals(Language.ENG))
            return translateEngToRus(word);
        else
            return translateRusToEng(word);
    }

    @Override
    public Language recognizeLanguage(String word) throws TranslatorException{
        YandexRecognitionResponse response;
        try {
            response = restTemplate.getForObject(
                    buildRecognitionURL(word),
                    YandexRecognitionResponse.class
            );
        } catch (RestClientException e){
            throw new TranslatorException(RECOGNITION_ERROR_MESSAGE, e);
        }
        return Language.get(response.getLang());
    }

    private YandexTranslationResponse translateWord(String word, Language source, Language target) throws TranslatorException {
        YandexTranslationResponse response;
        try {
            response = restTemplate.getForObject(
                    buildTranslationURL(word, source, target),
                    YandexTranslationResponse.class
            );
        } catch (RestClientException e) {
            throw new TranslatorException(TRANSLATION_ERROR_MESSAGE, e);
        }
        return response;
    }

    private String buildTranslationURL(String text, Language source, Language target){
        String lang = source.toString()+"-"+target.toString();
        return String.format(translationUrl, apiKey, text, lang);
    }

    private String buildRecognitionURL(String text){
        return String.format(langRecognitionUrl, apiKey, text);
    }

//    public String getApiKey() {
//        return apiKey;
//    }
//
//    public void setApiKey(String apiKey) {
//        this.apiKey = apiKey;
//    }
//
//    public String getTranslationUrl() {
//        return translationUrl;
//    }
//
//    public void setTranslationUrl(String translationUrl) {
//        this.translationUrl = translationUrl;
//    }
//
//    public String getLangRecognitionUrl() {
//        return langRecognitionUrl;
//    }
//
//    public void setLangRecognitionUrl(String langRecognitionUrl) {
//        this.langRecognitionUrl = langRecognitionUrl;
//    }
}
