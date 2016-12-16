package com.zheltoukhov.linguaneo.component;

import com.zheltoukhov.linguaneo.Constants;
import com.zheltoukhov.linguaneo.dto.StatisticDto;
import com.zheltoukhov.linguaneo.dto.word.WordDto;
import com.zheltoukhov.linguaneo.entity.Training;
import com.zheltoukhov.linguaneo.entity.Word;
import com.zheltoukhov.linguaneo.exception.LinguaneoException;
import com.zheltoukhov.linguaneo.repository.TrainingRepository;
import com.zheltoukhov.linguaneo.repository.WordRepository;
import com.zheltoukhov.linguaneo.service.TrainingService;
import com.zheltoukhov.linguaneo.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatisticHandler {
    @Autowired
    private WordService wordService;

    @Autowired
    private TrainingService trainingService;

    public StatisticDto getStatistics(){
        Long count = wordService.getCount();
        if (count == 0)
            throw new LinguaneoException(Constants.Messages.NO_DATA_FOR_STAT);
        Long learned = wordService.countByMistakeIndex(0);
        List<Training> trainings = trainingService.getAll();
        Integer allWords = 0, mistakes = 0;
        for(Training training : trainings){
            allWords+=training.getWordsAmount();
            mistakes+=training.getMistakesAmount();
        }
        Integer mistakesPercent = (int) ((mistakes * 100.0f) / allWords);
        Word hardestWord = wordService.getHardestWord();
        WordDto wordDto = hardestWord == null ? new WordDto("", "") : new WordDto(hardestWord.getEng(), hardestWord.getRus());
        return new StatisticDto(
                count,
                learned,
                mistakesPercent,
                trainings,
                wordDto
        );
    }
}
