package com.zheltoukhov.linguaneo.component;

import com.zheltoukhov.linguaneo.entity.WordsGroup;
import com.zheltoukhov.linguaneo.entity.Word;
import com.zheltoukhov.linguaneo.repository.WordsGroupRepository;
import com.zheltoukhov.linguaneo.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

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
        word1.setEngValue("Dog");
        word1.setRusValue("Собака");
        word1.setGroup(group);
        wordRepository.save(word1);

        Word word2 = new Word();
        word2.setEngValue("Cat");
        word2.setRusValue("Кошка");
        word2.setGroup(group);
        wordRepository.save(word2);


    }
}
