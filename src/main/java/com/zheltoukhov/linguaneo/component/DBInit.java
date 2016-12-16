package com.zheltoukhov.linguaneo.component;

import com.zheltoukhov.linguaneo.entity.WordsGroup;
import com.zheltoukhov.linguaneo.entity.Word;
import com.zheltoukhov.linguaneo.repository.WordsGroupRepository;
import com.zheltoukhov.linguaneo.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DBInit {

    @Autowired
    private WordsGroupRepository groupRepository;

    @Autowired
    private WordRepository wordRepository;

    @PostConstruct
    @Transactional
    public void init(){
        WordsGroup group = new WordsGroup();
        group.setName("Животные");
        group = groupRepository.save(group);
        createWord("cat","кошка", 3, group);
        createWord("dog","собака", 4, group);
        createWord("tiger","тигр", 3, group);
        createWord("fox","лиса", 0, group);
        createWord("monkey","обезьяна", 5, group);
        createWord("car","машина", 3, null);
        createWord("bus","автобус", 3, null);
        createWord("house","дом", 3, null);
        createWord("box","коробка", 0, null);
        createWord("world","мир", 1, null);


    }

    private Word createWord(String eng, String rus, Integer ind, WordsGroup group){
        Word word1 = new Word();
        word1.setEng(eng);
        word1.setRus(rus);
        word1.setMistakeIndex(ind);
        if(group!=null)
            word1.setWordsGroup(group);
        return wordRepository.save(word1);
    }
}
