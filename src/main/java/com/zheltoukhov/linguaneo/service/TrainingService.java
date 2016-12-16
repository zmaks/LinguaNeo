package com.zheltoukhov.linguaneo.service;

import com.zheltoukhov.linguaneo.component.DefaultWords;
import com.zheltoukhov.linguaneo.dto.training.TrainingCheckAnswer;
import com.zheltoukhov.linguaneo.dto.training.TrainingDto;
import com.zheltoukhov.linguaneo.dto.training.TrainingWordDto;
import com.zheltoukhov.linguaneo.entity.Training;
import com.zheltoukhov.linguaneo.entity.Word;
import com.zheltoukhov.linguaneo.exception.LinguaneoException;
import com.zheltoukhov.linguaneo.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.zheltoukhov.linguaneo.Constants.*;
import static com.zheltoukhov.linguaneo.Constants.Messages.NO_DATA_FOR_TRAINING;
import static com.zheltoukhov.linguaneo.Constants.Messages.NO_DATA_FOR__GROUP_TRAINING;

@Service
public class TrainingService {

    @Autowired
    private WordService wordService;

    @Autowired
    private TrainingRepository trainingRepository;

    public TrainingDto getTrainingData(Long groupId){
        if (wordService.getCount() < TRAINING_WORDS_MIN_AMOUNT)
            throw new LinguaneoException(NO_DATA_FOR_TRAINING);
        List<Word> words = new ArrayList<Word>();
        if (groupId != null) {
            words.addAll(wordService.getByGroupId(groupId));
            if (words.size() < TRAINING_WORDS_MIN_AMOUNT)
                throw new LinguaneoException(NO_DATA_FOR__GROUP_TRAINING);
        } else
            words.addAll(generateWordsList());
        Training training = create();
        List<TrainingWordDto> trainingWordList = createTrainingWords(words, groupId);
        updateTrainingWordsAmount(training, trainingWordList.size());
        return new TrainingDto(training.getId(), trainingWordList);
    }

    public TrainingCheckAnswer checkAnswer(TrainingCheckAnswer answer){
        Word word = wordService.getByEngValue(answer.getQuestion());
        boolean isAnswerCorrect = answer.getAnswer().equalsIgnoreCase(word.getRus());
        Integer mistakeInd = getNewMistakeIndex(isAnswerCorrect, word.getMistakeIndex());
        answer.setCorrect(isAnswerCorrect);
        wordService.update(word, mistakeInd);
        return answer;
    }

    @Transactional
    public Training update(Long id, Integer mistakes){
        Training training = trainingRepository.findOne(id);
        training.setMistakesAmount(mistakes);
        training.setTrainingDate(new Date());
        return trainingRepository.save(training);
    }

    @Transactional
    private Training create(){
        Training training = new Training();
        training.setTrainingDate(new Date());
        training.setWordsAmount(TRAINING_WORDS_AMOUNT);
        return trainingRepository.save(training);
    }

    public List<Training> getAll(){
        List<Training> trainings = new ArrayList<Training>();
        for (Training training : trainingRepository.findAll())
            trainings.add(training);
        return trainings;
    }

    private List<Word> generateWordsList(){
        LinkedList<Word> result = new LinkedList<Word>();
        Integer hardestWordsAmount = Math.round(TRAINING_WORDS_AMOUNT * HARDEST_WORDS_PERCENT);
        List<Word> oldest = wordService.getOldest(TRAINING_WORDS_AMOUNT);//-result.size()));
        result.addAll(oldest);
        if(result.size()==TRAINING_WORDS_AMOUNT){
            List<Word> hardest = wordService.getHardest(hardestWordsAmount);
            for(Word word : hardest){
                if (!result.contains(word)){
                    result.addFirst(word);
                    result.removeLast();
                }
            }
        }
        return result   ;
    }

    private List<TrainingWordDto> createTrainingWords(List<Word> words, Long groupId){
        List<TrainingWordDto> result = new ArrayList<TrainingWordDto>();
        for (Word word : words) {
            result.add(new TrainingWordDto(word.getEng(), generateAnswersWords(word, groupId)));
        }
        Collections.shuffle(result);
        return result;
    }

    private Set<String> generateAnswersWords(Word word, Long groupId) {
        Set<String> answers = new HashSet<String>();
        answers.add(word.getRus());
        List<Word> words;
        if (groupId != null) {
            words = wordService.getByGroupId(groupId, ANSWERS_WORDS_AMOUNT);
        } else {
            words = wordService.getAllWords();
        }
        Collections.shuffle(words);
        int count = words.size() < ANSWERS_WORDS_AMOUNT-1 ? words.size() : ANSWERS_WORDS_AMOUNT-1;
        for(int i = 0; i < count; i++){
            answers.add(words.get(i).getRus());
        }
        while (answers.size() < ANSWERS_WORDS_AMOUNT){
            answers.add(DefaultWords.getWord());
        }
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

    @Transactional
    private void updateTrainingWordsAmount(Training training, int size) {
        training.setWordsAmount(size);
        trainingRepository.save(training);
    }
}
