package com.zheltoukhov.linguaneo.dto;

/**
 * Created by Maksim on 11.12.2016.
 */
public class TrainingCheckAnswer {
    private boolean isCorrect;
    private String answer;
    private String question;

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public TrainingCheckAnswer(boolean isCorrect, String answer) {

        this.isCorrect = isCorrect;
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
