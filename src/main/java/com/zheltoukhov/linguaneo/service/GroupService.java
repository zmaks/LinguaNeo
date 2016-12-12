package com.zheltoukhov.linguaneo.service;

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
    public WordsGroup create(String name){
        WordsGroup group = new WordsGroup();
        group.setName(name);
        return groupRepository.save(group);
    }

    @Transactional
    public void delete(WordsGroup group){
        groupRepository.delete(group);
    }

    @Transactional
    public void delete(Long groupId){
        groupRepository.delete(groupId);
    }

    @Transactional
    public WordsGroup getById(Long id){
        return groupRepository.findOne(id);
    }
}
