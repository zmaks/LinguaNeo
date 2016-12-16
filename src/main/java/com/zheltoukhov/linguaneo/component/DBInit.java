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

/**
 * Created by Maksim on 07.12.2016.
 */
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
        group.setName("GR1");
        groupRepository.save(group);

        Word word1 = new Word();
        word1.setEng("dog");
        word1.setRus("собака");
        word1.setMistakeIndex(3);
        word1.setWordsGroup(group);
        wordRepository.save(word1);

        Word word2 = new Word();
        word2.setEng("cat");
        word2.setRus("кошка");
        word2.setMistakeIndex(0);
        word2.setWordsGroup(group);
        wordRepository.save(word2);

    }
}
