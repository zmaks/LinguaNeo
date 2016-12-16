package com.zheltoukhov.linguaneo.dto;

import com.zheltoukhov.linguaneo.dto.word.WordDto;
import com.zheltoukhov.linguaneo.entity.Training;

import java.util.List;

public class StatisticDto {
    private Long allCount;
    private Long learnedCount;
    private Integer mistakesPercent;
    private List<Training> trainings;
    private WordDto difficultWord;

    public StatisticDto() {
    }

    public StatisticDto(Long allCount, Long learnedCount, Integer mistakesPercent, List<Training> trainings, WordDto difficultWord) {
        this.allCount = allCount;
        this.learnedCount = learnedCount;
        this.mistakesPercent = mistakesPercent;
        this.trainings = trainings;
        this.difficultWord = difficultWord;
    }

    public Long getAllCount() {
        return allCount;
    }

    public void setAllCount(Long allCount) {
        this.allCount = allCount;
    }

    public Long getLearnedCount() {
        return learnedCount;
    }

    public void setLearnedCount(Long learnedCount) {
        this.learnedCount = learnedCount;
    }

    public Integer getMistakesPercent() {
        return mistakesPercent;
    }

    public void setMistakesPercent(Integer mistakesPercent) {
        this.mistakesPercent = mistakesPercent;
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    public WordDto getDifficultWord() {
        return difficultWord;
    }

    public void setDifficultWord(WordDto difficultWord) {
        this.difficultWord = difficultWord;
    }
}
