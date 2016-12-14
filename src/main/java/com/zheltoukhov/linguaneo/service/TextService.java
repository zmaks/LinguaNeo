package com.zheltoukhov.linguaneo.service;

import com.zheltoukhov.linguaneo.dto.translation.TranslationWordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.zheltoukhov.linguaneo.Constants.WORD_REGEXP_IN_TEXT_REGEXP;

/**
 * Created by mazh0416 on 12/8/2016.
 */
@Service
public class TextService {

    @Autowired
    private WordService wordService;

    public Set<TranslationWordDto> parseAndTranslateText(String text){
        Set<TranslationWordDto> result = new HashSet<TranslationWordDto>();
        Set<String> words = new HashSet<String>();
        Matcher matcher = Pattern.compile(WORD_REGEXP_IN_TEXT_REGEXP).matcher(text);
        while(matcher.find()) {
            words.add(matcher.group());
        }
        for(String word : words){
            result.add(wordService.translateWord(word));
        }
        return result;
    }
}
