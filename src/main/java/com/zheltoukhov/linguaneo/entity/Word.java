package com.zheltoukhov.linguaneo.entity;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Maksim on 07.12.2016.
 */
@Entity
public class Word implements CommonEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String engValue;
    private String rusValue;
    private Integer mistakeIndex = 4;
    private Date lastUsage;

    @ManyToOne()
    @JoinColumn(name = "groupId")
    private WordsGroup group;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEngValue() {
        return engValue;
    }

    public void setEngValue(String engValue) {
        this.engValue = engValue;
    }

    public String getRusValue() {
        return rusValue;
    }

    public void setRusValue(String rusValue) {
        this.rusValue = rusValue;
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

    public WordsGroup getGroup() {
        return group;
    }

    public void setGroup(WordsGroup group) {
        this.group = group;
    }
}
