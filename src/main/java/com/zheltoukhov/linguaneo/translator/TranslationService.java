package com.zheltoukhov.linguaneo.translator;

/**
 * Created by Maksim on 07.12.2016.
 */
public interface TranslationService {
    TranslationResponse translateEngToRus(String word);
    TranslationResponse translateRusToEng(String word);
}
