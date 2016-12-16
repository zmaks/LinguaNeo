package com.zheltoukhov.linguaneo.dto.training;

import java.util.Set;

public class TrainingWordDto {
    private String question;
    private Set<String> answers;

    public TrainingWordDto(String question, Set<String> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Set<String> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<String> answers) {
        this.answers = answers;
    }
}
