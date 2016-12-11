package com.zheltoukhov.linguaneo.dto;

import java.util.List;

/**
 * Created by Maksim on 10.12.2016.
 */
public class TrainingWordDto {
    private String question;
    private List<String> answers;

    public TrainingWordDto(String question, List<String> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
