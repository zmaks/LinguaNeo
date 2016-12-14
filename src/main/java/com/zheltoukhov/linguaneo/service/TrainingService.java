package com.zheltoukhov.linguaneo.service;

import com.zheltoukhov.linguaneo.dto.training.TrainingCheckAnswer;
import com.zheltoukhov.linguaneo.dto.training.TrainingDto;
import com.zheltoukhov.linguaneo.dto.training.TrainingWordDto;
import com.zheltoukhov.linguaneo.entity.Training;
import com.zheltoukhov.linguaneo.entity.Word;
import com.zheltoukhov.linguaneo.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.zheltoukhov.linguaneo.Constants.*;

/**
 * Created by Maksim on 10.12.2016.
 */
@Service
public class TrainingService {

    @Autowired
    private WordService wordService;

    private GroupService groupService;

    @Autowired
    private TrainingRepository trainingRepository;

    public TrainingDto getTrainingData(Long groupId){
        List<Word> words = new ArrayList<Word>();
        if (groupId != null)
            words.addAll(wordService.getByGroupId(groupId));
        else
            words.addAll(generateWordsList());
        Training training = createTraining();
        return new TrainingDto(training.getId(), createTrainingWords(words));
    }

    public TrainingCheckAnswer checkAnswer(TrainingCheckAnswer answer){
        Word word = wordService.getByEngValue(answer.getQuestion());
        if (word == null) throw new IllegalArgumentException("This word has been already deleted");
        boolean isAnswerCorrect = answer.getAnswer().equalsIgnoreCase(word.getRus());
        Integer mistakeInd = getNewMistakeIndex(isAnswerCorrect, word.getMistakeIndex());
        answer.setCorrect(isAnswerCorrect);
        wordService.update(word, mistakeInd);
        return answer;
    }

    @Transactional
    private Training createTraining(){
        Training training = new Training();
        training.setTrainingDate(new Date());
        training.setWordsAmount(TRAINING_WORDS_AMOUNT);
        return trainingRepository.save(training);
    }

    private Set<Word> generateWordsList(){
        Set<Word> result = new HashSet<Word>(TRAINING_WORDS_AMOUNT);
        Integer hardestWordsAmount = Math.round(TRAINING_WORDS_AMOUNT * HARDEST_WORDS_PERCENT);
        result.addAll(wordService.getHardest(hardestWordsAmount));
        result.addAll(wordService.getOldest(TRAINING_WORDS_AMOUNT-result.size()));
        return result;
    }

    private List<TrainingWordDto> createTrainingWords(List<Word> words){
        List<TrainingWordDto> result = new ArrayList<TrainingWordDto>();
        for (Word word : words) {
            result.add(new TrainingWordDto(word.getEng(), generateAnswersWords(word)));
        }
        Collections.shuffle(result);
        return result;
    }

    private List<String> generateAnswersWords(Word word) {
        List<String> answers = new ArrayList<String>();
        answers.add(word.getRus());
        while (answers.size() < ANSWERS_WORDS_AMOUNT){
            // TODO: Add default words
            answers.add("---------");
        }
        Collections.shuffle(answers);
        return answers;
    }

    private Integer getNewMistakeIndex(boolean isAnswerCorrect, Integer mistakeInd){
        Integer resultInd;
        if(isAnswerCorrect)
            resultInd = mistakeInd != 0 ? mistakeInd-1 : 0;
        else
            resultInd = mistakeInd > DEFAULT_MISTAKE_INDEX ? mistakeInd+1 : DEFAULT_MISTAKE_INDEX+1;
        return resultInd;
    }
}
