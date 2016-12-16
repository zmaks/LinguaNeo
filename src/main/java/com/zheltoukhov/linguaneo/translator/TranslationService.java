package com.zheltoukhov.linguaneo.translator;

import com.zheltoukhov.linguaneo.translator.exception.TranslatorException;

public interface TranslationService {

    TranslationDto translateEngToRus(String word) throws TranslatorException;
    TranslationDto translateRusToEng(String word) throws TranslatorException;
    TranslationDto translateWord(String word) throws TranslatorException;
    Language recognizeLanguage(String word) throws TranslatorException;
}
