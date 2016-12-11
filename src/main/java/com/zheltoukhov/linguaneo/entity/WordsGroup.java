package com.zheltoukhov.linguaneo.entity;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Maksim on 07.12.2016.
 */
@Entity
public class WordsGroup {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private boolean isHidden = false;

    @ManyToMany
    @JoinTable(name = "group_word",
            joinColumns = @JoinColumn(name = "word_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Word> words = new ArrayList<Word>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public void addWord(Word word){
        words.add(word);
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
