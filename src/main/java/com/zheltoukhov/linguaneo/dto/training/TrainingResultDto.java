package com.zheltoukhov.linguaneo.dto.training;

public class TrainingResultDto {
    private Long id;
    private Integer mistakes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMistakes() {
        return mistakes;
    }

    public void setMistakes(Integer mistakes) {
        this.mistakes = mistakes;
    }
}
