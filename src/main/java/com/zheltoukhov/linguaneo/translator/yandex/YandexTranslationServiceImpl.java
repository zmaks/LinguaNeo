package com.zheltoukhov.linguaneo.translator.yandex;

import com.zheltoukhov.linguaneo.translator.Language;
import com.zheltoukhov.linguaneo.translator.TranslationResponse;
import com.zheltoukhov.linguaneo.translator.TranslationService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

/**
 * Created by Maksim on 07.12.2016.
 */
@Service
public class YandexTranslationServiceImpl implements TranslationService {

    private static final String API_KEY = "trnsl.1.1.20161207T203906Z.6cfcebdfd2cc4415.c95493d50fe2b4565007485d39a6efad828591a0";
    private static final String URL = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=%s&text=%s&lang=%s";
    private RestTemplate restTemplate;

    @PostConstruct
    void init(){
        restTemplate = new RestTemplate();
    }
    public TranslationResponse translateEngToRus(String word) {
        YandexTranslationResponse response = restTemplate.getForObject(
                buildURL(word, Language.ENG, Language.RUS),
                YandexTranslationResponse.class
        );

        return response;
    }

    public TranslationResponse translateRusToEng(String word) {
        YandexTranslationResponse response = restTemplate.getForObject(
                buildURL(word, Language.RUS, Language.ENG),
                YandexTranslationResponse.class
        );

        return response;
    }

    private String buildURL(String text, Language source, Language target){
        String lang = source.toString()+"-"+target.toString();
        return String.format(URL, API_KEY, text, lang);
    }

}
