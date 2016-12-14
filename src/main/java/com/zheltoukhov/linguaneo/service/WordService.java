package com.zheltoukhov.linguaneo.service;

import com.zheltoukhov.linguaneo.dto.translation.TranslationWordDto;
import com.zheltoukhov.linguaneo.dto.word.UpdateGroupDto;
import com.zheltoukhov.linguaneo.dto.word.WordDto;
import com.zheltoukhov.linguaneo.entity.Word;
import com.zheltoukhov.linguaneo.repository.WordRepository;
import com.zheltoukhov.linguaneo.translator.Language;
import com.zheltoukhov.linguaneo.translator.TranslationDto;
import com.zheltoukhov.linguaneo.translator.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.zheltoukhov.linguaneo.Constants.*;

/**
 * Created by Maksim on 07.12.2016.
 */

@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private GroupService groupService;

    @Autowired
    private TranslationService translationService;

    public TranslationWordDto translateWord(@NotNull String word){
        TranslationWordDto result;
        word = word.toLowerCase();
        Language language = translationService.recognizeLanguage(word);
        Word wordEntity = getWordFromDictionary(word, language);
        if (wordEntity == null){
            TranslationDto translation = translationService.translateWord(word);
            result = new TranslationWordDto(translation.getEngValue(), translation.getRusValue(), false);
        } else {
            result = new TranslationWordDto(wordEntity.getEng(), wordEntity.getRus(), true);
        }
        return result;
    }

    @Transactional
    public Word getWordFromDictionary(String word, Language language){
        Word foundWord;
        if (Language.ENG.equals(language)){
            foundWord = wordRepository.findByEngValue(word);
        } else {
            foundWord = wordRepository.findByRusValue(word);
        }
        return foundWord;
    }

    @Transactional
    public Word create(WordDto wordDto){
        Word word = new Word();
        word.setEng(wordDto.getEng().toLowerCase());
        word.setRus(wordDto.getRus().toLowerCase());
        word.setLastUsage(new Date());
        word.setMistakeIndex(DEFAULT_MISTAKE_INDEX);
        word = wordRepository.save(word);
        return word;
    }

    public List<Word> create(List<TranslationWordDto> wordDtoList){
        List<Word> result = new ArrayList<Word>();
        for (TranslationWordDto wordDto : wordDtoList){
            Word word = create(wordDto);
            result.add(word);
        }
        return result;
    }

    @Transactional
    public List<Word> getAllWords(){
        List<Word> words = new ArrayList<Word>();
        for(Word word : wordRepository.findAll())
            words.add(word);
        return words;
        /*return stream(wordRepository.findAll().spliterator(), false)
                .collect(toList());*/
    }

    @Transactional
    public Word update(Word word, Integer mistakeInd){
        word.setLastUsage(new Date());
        word.setMistakeIndex(mistakeInd);
        return wordRepository.save(word);
    }

    @Transactional
    public Word getByRusValue(String value){
        return wordRepository.findByRusValue(value);
    }

    @Transactional
    public Word getByEngValue(String value){
        return wordRepository.findByEngValue(value);
    }

    @Transactional
    public List<Word> getOldest(Integer amount){
        return wordRepository.findOldest(amount);
    }

    @Transactional
    public List<Word> getHardest(Integer amount){
        return wordRepository.findHardest(amount, DEFAULT_MISTAKE_INDEX);
    }

    @Transactional
    public List<Word> getByGroupId(Long groupId){
        return wordRepository.findByGroupId(groupId);
    }

    @Transactional
    public Word updateGroup(UpdateGroupDto updateGroupDto){
        Word word = wordRepository.findOne(updateGroupDto.getWordId());
        word.setWordsGroup(groupService.getById(updateGroupDto.getGroupId()));
        return wordRepository.save(word);
    }

    @Transactional
    public void delete(Long id) {
        wordRepository.delete(id);
    }
}
