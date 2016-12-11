package com.zheltoukhov.linguaneo.entity;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Maksim on 07.12.2016.
 */
@Entity
public class Word{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String eng;
    private String rus;
    private Integer mistakeIndex;
    private Date lastUsage = new Date();

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public String getRus() {
        return rus;
    }

    public void setRus(String rus) {
        this.rus = rus;
    }

    public Integer getMistakeIndex() {
        return mistakeIndex;
    }

    public void setMistakeIndex(Integer mistakeIndex) {
        this.mistakeIndex = mistakeIndex;
    }

    public Date getLastUsage() {
        return lastUsage;
    }

    public void setLastUsage(Date lastUsage) {
        this.lastUsage = lastUsage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return id.equals(word.id);
    }
}
