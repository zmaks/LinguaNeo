package com.zheltoukhov.linguaneo.service;

import com.zheltoukhov.linguaneo.dto.TranslationWordDto;
import com.zheltoukhov.linguaneo.entity.Word;
import com.zheltoukhov.linguaneo.repository.WordRepository;
import com.zheltoukhov.linguaneo.translator.TranslationResponse;
import com.zheltoukhov.linguaneo.translator.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Maksim on 07.12.2016.
 */

@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private TranslationService translationService;

    public TranslationWordDto translateWord(String word){
        TranslationResponse translationResponse = translationService.translateEngToRus(word);

        return new TranslationWordDto(word, translationResponse.getText()[0]);
    }
}
