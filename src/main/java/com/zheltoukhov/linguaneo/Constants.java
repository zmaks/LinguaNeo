package com.zheltoukhov.linguaneo;

public interface Constants {
    String WORD_REGEXP_IN_TEXT_REGEXP = "%[a-zA-ZА-Яа-я]{3,20}%";
    String WORD_REGEXP = "^[a-zA-ZА-Яа-я]{1,15}\\s?[a-zA-ZА-Яа-я]{1,15}$";
    Integer DEFAULT_MISTAKE_INDEX = 3;
    Integer TRAINING_WORDS_AMOUNT = 5;
    Integer TRAINING_WORDS_MIN_AMOUNT = 2;
    Float HARDEST_WORDS_PERCENT = 0.3f;
    Integer ANSWERS_WORDS_AMOUNT = 4;
    Integer MAX_TEXT_LENGTH = 200;

   interface Messages {
       String WORD_VALIDATION_MESSAGE = "Слово введено некорректно";
       String TEXT_VALIDATION_MESSAGE = "Слишком длинный текст";
       String GROUP_NAME_VALIDATION_MESSAGE = "Название группы введено некорректно";
       String EXCEPTION_MESSAGE = "Ошибка на сервере! Сервис временно не доступен.";
       String NO_WORDS_IN_TEXT_MESSAGE = "Слова в тексте не найдены";
       String NO_DATA_FOR_TRAINING = "В словаре недостаточно слов для тренировки";
       String NO_DATA_FOR__GROUP_TRAINING = "В группе недостаточно слов для тренировки";
       String NO_CONTENT_FOR_WORD_OBJECT = "Невозможно добавить слово без английского или русского перевода";
       String NO_DATA_FOR_STAT = "Недостаточно данных для рассчета статистики";
   }
}
