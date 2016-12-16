package com.zheltoukhov.linguaneo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Training {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private Integer wordsAmount;
    private Integer mistakesAmount = 0;
    private Date trainingDate;

    public Integer getMistakesAmount() {
        return mistakesAmount;
    }

    public void setMistakesAmount(Integer mistakesAmount) {
        this.mistakesAmount = mistakesAmount;
    }

    public Date getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(Date trainingDate) {
        this.trainingDate = trainingDate;
    }

    public Integer getWordsAmount() {
        return wordsAmount;
    }

    public void setWordsAmount(Integer wordsAmount) {
        this.wordsAmount = wordsAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
