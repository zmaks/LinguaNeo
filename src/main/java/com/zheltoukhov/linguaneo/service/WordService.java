package com.zheltoukhov.linguaneo.service;

import com.zheltoukhov.linguaneo.dto.translation.TranslationWordDto;
import com.zheltoukhov.linguaneo.dto.word.UpdateGroupDto;
import com.zheltoukhov.linguaneo.dto.word.WordDto;
import com.zheltoukhov.linguaneo.entity.Word;
import com.zheltoukhov.linguaneo.exception.LinguaneoException;
import com.zheltoukhov.linguaneo.repository.WordRepository;
import com.zheltoukhov.linguaneo.translator.Language;
import com.zheltoukhov.linguaneo.translator.TranslationDto;
import com.zheltoukhov.linguaneo.translator.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.zheltoukhov.linguaneo.Constants.*;
import static com.zheltoukhov.linguaneo.Constants.Messages.NO_CONTENT_FOR_WORD_OBJECT;

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
            foundWord = wordRepository.findByEng(word);
        } else {
            foundWord = wordRepository.findByRus(word);
        }
        return foundWord;
    }

    @Transactional
    public Word create(WordDto wordDto){
        if(wordDto.getEng() == null || wordDto.getRus() == null)
            throw new LinguaneoException(NO_CONTENT_FOR_WORD_OBJECT);
        Word word = new Word();
        /*if(wordRepository.findByEng(wordDto.getEng()) == null &&
                wordRepository.findByRus(wordDto.getRus()) == null){*/
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

    public List<Word> getByAmount(Integer amount){
        return wordRepository.findAll(new PageRequest(0,amount)).getContent();
    }

    @Transactional
    public Word update(Word word, Integer mistakeInd){
        word.setLastUsage(new Date());
        word.setMistakeIndex(mistakeInd);
        return wordRepository.save(word);
    }

    @Transactional
    public Word getByRusValue(String value){
        return wordRepository.findByRus(value);
    }

    @Transactional
    public Word getByEngValue(String value){
        return wordRepository.findByEng(value);
    }

    @Transactional
    public List<Word> getOldest(Integer amount){
        //return wordRepository.findOldest(amount);
        return wordRepository.findByOrderByLastUsageAsc(new PageRequest(0, amount));
    }

    @Transactional
    public List<Word> getHardest(Integer amount){
        return wordRepository.findHardest(amount, DEFAULT_MISTAKE_INDEX);
    }

    @Transactional
    public List<Word> getByGroupId(Long groupId){
        return wordRepository.findByWordsGroupId(groupId);
    }

    @Transactional
    public List<Word> getByGroupId(Long groupId, Integer amount) {
        return wordRepository.findByWordsGroupId(groupId, new PageRequest(0, amount));
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

    public Long countByMistakeIndex(Integer mistakeIndex){
        return wordRepository.countByMistakeIndex(mistakeIndex);
    }

    public Long getCount(){
        return wordRepository.count();
    }

    public Word getHardestWord(){
        return wordRepository.findTopByOrderByMistakeIndexDesc();
    }
}
