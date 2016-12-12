package com.zheltoukhov.linguaneo.validation;

import com.zheltoukhov.linguaneo.validation.annotation.ValidText;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.zheltoukhov.linguaneo.Constants.MAX_WORDS_IN_TEXT_AMOUNT;
import static com.zheltoukhov.linguaneo.Constants.WORD_REGEXP_IN_TEXT_REGEXP;

/**
 * Created by mazh0416 on 12/12/2016.
 */
public class TextValidator implements ConstraintValidator<ValidText, String> {
    @Override
    public void initialize(ValidText validText) {}

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Matcher matcher = Pattern.compile(WORD_REGEXP_IN_TEXT_REGEXP).matcher(s);
        int count = 0;
        while (matcher.find())
            count++;
        return count > 0 && count <= MAX_WORDS_IN_TEXT_AMOUNT;
    }
}
