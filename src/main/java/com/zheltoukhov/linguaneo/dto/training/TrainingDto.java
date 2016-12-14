package com.zheltoukhov.linguaneo.dto.training;

import java.util.List;

/**
 * Created by Maksim on 10.12.2016.
 */
public class TrainingDto {
    private Long id;
    private List<TrainingWordDto> words;

    public TrainingDto(Long id, List<TrainingWordDto> words) {
        this.id = id;
        this.words = words;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TrainingWordDto> getWords() {
        return words;
    }

    public void setWords(List<TrainingWordDto> words) {
        this.words = words;
    }
}
