package com.zheltoukhov.linguaneo.validation;

import com.zheltoukhov.linguaneo.validation.annotation.ValidWord;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

import static com.zheltoukhov.linguaneo.Constants.WORD_REGEXP;

public class WordValidator implements ConstraintValidator<ValidWord, String> {
    @Override
    public void initialize(ValidWord validWord) {}

    @Override
    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
        return Pattern.matches(WORD_REGEXP, str);
    }
}
