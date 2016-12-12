package com.zheltoukhov.linguaneo.validation;

import com.zheltoukhov.linguaneo.validation.annotation.ValidWord;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

import static com.zheltoukhov.linguaneo.Constants.WORD_REGEXP;

/**
 * Created by mazh0416 on 12/12/2016.
 */
public class WordValidator implements ConstraintValidator<ValidWord, String> {
    @Override
    public void initialize(ValidWord validWord) {}

    @Override
    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
        return Pattern.matches(WORD_REGEXP, str);
    }
}
