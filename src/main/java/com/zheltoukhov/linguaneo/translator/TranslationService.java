package com.zheltoukhov.linguaneo.translator;

/**
 * Created by Maksim on 07.12.2016.
 */
public interface TranslationService {

    TranslationDto translateEngToRus(String word);
    TranslationDto translateRusToEng(String word);
    TranslationDto translateWord(String word);
    Language recognizeLanguage(String word);
}
