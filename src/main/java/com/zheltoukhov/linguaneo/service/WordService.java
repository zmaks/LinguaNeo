package com.zheltoukhov.linguaneo.service;

import com.zheltoukhov.linguaneo.dto.TranslationWordDto;
import com.zheltoukhov.linguaneo.entity.Word;
import com.zheltoukhov.linguaneo.repository.WordRepository;
import com.zheltoukhov.linguaneo.translator.Language;
import com.zheltoukhov.linguaneo.translator.TranslationDto;
import com.zheltoukhov.linguaneo.translator.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        TranslationWordDto result;
        Language language = translationService.recognizeLanguage(word);
        Word wordEntity = getWordFromDictionary(word, language);
        if (wordEntity == null){
            TranslationDto translation = translationService.translateWord(word);
            result = new TranslationWordDto(translation.getEngValue(), translation.getRusValue(), false);
        } else {
            result = new TranslationWordDto(wordEntity.getEngValue(), wordEntity.getRusValue(), true);
        }
        return result;
    }

    public Word getWordFromDictionary(String word, Language language){
        Word foundWord = null;
        if (Language.ENG.equals(language)){
            foundWord = wordRepository.findByEngValue(word);
        } else {
            foundWord = wordRepository.findByRusValue(word);
        }
        return foundWord;
    }

    public Word saveWord(TranslationWordDto wordDto){
        Word word = new Word();
        word.setEngValue(wordDto.getEngValue());
        word.setRusValue(wordDto.getRusValue());
        word.setLastUsage(new Date());
        word = wordRepository.save(word);
        return word;
    }

    public List<Word> saveWords(List<TranslationWordDto> wordDtoList){
        List<Word> result = new ArrayList<Word>();
        for (TranslationWordDto wordDto : wordDtoList){
            Word word = saveWord(wordDto);
            result.add(word);
        }
        return result;
    }
    public List<Word> getAllWords(){

        // ?????
        //return wordRepository.findAll();
        return null;
    }
}
