package com.zheltoukhov.linguaneo.service;

import com.zheltoukhov.linguaneo.dto.TranslationWordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mazh0416 on 12/8/2016.
 */
@Service
public class TextService {

    private static final String PARSE_TEXT_REGEXP = "[a-zA-ZА-Яа-я]{3,}";

    @Autowired
    private WordService wordService;

    public List<TranslationWordDto> parseAndTranslateText(String text){
        //List<String> parsedText = new ArrayList<String>();
        List<TranslationWordDto> result = new ArrayList<TranslationWordDto>();
        Matcher matcher = Pattern.compile(PARSE_TEXT_REGEXP).matcher(text);
        while(matcher.find()) {
            result.add(wordService.translateWord(matcher.group()));
        }
        return result;
    }
}
