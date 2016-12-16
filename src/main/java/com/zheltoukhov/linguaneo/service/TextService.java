package com.zheltoukhov.linguaneo.service;

import com.zheltoukhov.linguaneo.dto.translation.TranslationWordDto;
import com.zheltoukhov.linguaneo.exception.LinguaneoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.zheltoukhov.linguaneo.Constants.Messages.NO_WORDS_IN_TEXT_MESSAGE;
import static com.zheltoukhov.linguaneo.Constants.WORD_REGEXP_IN_TEXT_REGEXP;

@Service
public class TextService {

    @Autowired
    private WordService wordService;

    public Set<TranslationWordDto> parseAndTranslateText(String text){
        Set<TranslationWordDto> result = new HashSet<TranslationWordDto>();
        Set<String> words = parseText(text);
        if (words.size() == 0)
            throw new LinguaneoException(NO_WORDS_IN_TEXT_MESSAGE);
        for(String word : words){
            result.add(wordService.translateWord(word));
        }
        return result;
    }

    public Set<String> parseText(String text){
        Set<String> words = new HashSet<String>();
        text = ("%"+text+"%").replaceAll("[,.?!;:''\"\\s]", "%%");
        Matcher matcher = Pattern.compile(WORD_REGEXP_IN_TEXT_REGEXP).matcher(text);
        while(matcher.find()) {
            words.add(matcher.group().replace("%",""));
        }
        return words;
    }
}
