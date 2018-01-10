package com.zheltoukhov.linguaneo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:global.properties")
public class Constants {
    public static final String WORD_REGEXP_IN_TEXT_REGEXP = "%[a-zA-ZА-Яа-я]{3,20}%";
    public static final String WORD_REGEXP = "^[a-zA-ZА-Яа-я]{1,15}\\s?[a-zA-ZА-Яа-я]{1,15}$";
    public static final Integer DEFAULT_MISTAKE_INDEX = 3;
    public static final Integer TRAINING_WORDS_AMOUNT = 5;
    public static final Integer TRAINING_WORDS_MIN_AMOUNT = 2;
    public static final Float HARDEST_WORDS_PERCENT = 0.3f;
    public static final Integer ANSWERS_WORDS_AMOUNT = 4;
    public static final Integer MAX_TEXT_LENGTH = 200;

    @Value("${api-key}")
    private String apiKey;
    @Value("${translation-url}")
    private String translationUrl;
    @Value("${recognition-url}")
    private String langRecognitionUrl;

    public String getApiKey() {
        return apiKey;
    }

    public String getTranslationUrl() {
        return translationUrl;
    }

    public String getLangRecognitionUrl() {
        return langRecognitionUrl;
    }

    public class Messages {
       public static final String WORD_VALIDATION_MESSAGE = "Слово введено некорректно";
       public static final String TEXT_VALIDATION_MESSAGE = "Слишком длинный текст";
       public static final String GROUP_NAME_VALIDATION_MESSAGE = "Название группы введено некорректно";
       public static final String EXCEPTION_MESSAGE = "Ошибка на сервере! Сервис временно не доступен.";
       public static final String NO_WORDS_IN_TEXT_MESSAGE = "Слова в тексте не найдены";
       public static final String NO_DATA_FOR_TRAINING = "В словаре недостаточно слов для тренировки";
       public static final String NO_DATA_FOR__GROUP_TRAINING = "В группе недостаточно слов для тренировки";
       public static final String NO_CONTENT_FOR_WORD_OBJECT = "Невозможно добавить слово без английского или русского перевода";
       public static final String NO_DATA_FOR_STAT = "Недостаточно данных для рассчета статистики";
   }
}
