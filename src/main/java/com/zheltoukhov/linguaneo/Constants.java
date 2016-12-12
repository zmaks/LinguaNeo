package com.zheltoukhov.linguaneo;

/**
 * Created by Maksim on 10.12.2016.
 */
public interface Constants {
    String WORD_REGEXP_IN_TEXT_REGEXP = "[a-zA-ZА-Яа-я]{3,20}";
    String WORD_REGEXP = "^[a-zA-ZА-Яа-я]{1,15}\\s?[a-zA-ZА-Яа-я]{1,15}$";
    Integer DEFAULT_MISTAKE_INDEX = 3;
    Integer TRAINING_WORDS_AMOUNT = 5;
    Float HARDEST_WORDS_PERCENT = 0.3f;
    Integer ANSWERS_WORDS_AMOUNT = 4;
    Integer MAX_WORDS_IN_TEXT_AMOUNT = 20;

   interface Messages {
       String WORD_VALIDATION_MESSAGE = "Слово введено некорректно";
       String TEXT_VALIDATION_MESSAGE = "В тексте слишком много слов либо слов не обнаружено";
   }
}
