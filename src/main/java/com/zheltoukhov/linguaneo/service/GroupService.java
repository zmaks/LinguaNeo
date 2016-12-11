package com.zheltoukhov.linguaneo.service;

import com.zheltoukhov.linguaneo.entity.Word;
import com.zheltoukhov.linguaneo.entity.WordsGroup;
import com.zheltoukhov.linguaneo.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maksim on 10.12.2016.
 */
@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Transactional
    public List<WordsGroup> getAllGroups(){
        List<WordsGroup> groups = new ArrayList<WordsGroup>();
        for(WordsGroup group : groupRepository.findAll())
            groups.add(group);
        return groups;
        /*return stream(wordRepository.findAll().spliterator(), false)
                .collect(toList());*/
    }

    @Transactional
    public void addWord(Long groupId, Word word){
        WordsGroup group = groupRepository.findOne(groupId);
        group.addWord(word);
        groupRepository.save(group);
    }

    @Transactional
    public List<Word> getWords(Long groupId){
        return groupRepository.findOne(groupId).getWords();
    }
}
