package com.zheltoukhov.linguaneo.service;

import com.zheltoukhov.linguaneo.dto.TranslationWordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mazh0416 on 12/8/2016.
 */
@Service
public class TextService {

    private static final String PARSE_TEXT_REGEXP = "\\[a-zA-ZА-Яа-я]{3,}";

    @Autowired
    private WordService wordService;

    public List<TranslationWordDto> parseText(String text){
        //List<String> parsedText = new ArrayList<String>();
        List<TranslationWordDto> result = new ArrayList<TranslationWordDto>();
        String[] parsedText = text.split(PARSE_TEXT_REGEXP);
        for (String word : parsedText){
            result.add(wordService.translateWord(word));
        }
        return result;
    }
}
