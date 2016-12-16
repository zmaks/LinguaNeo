package com.zheltoukhov.linguaneo.validation;

import com.zheltoukhov.linguaneo.validation.annotation.ValidText;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.zheltoukhov.linguaneo.Constants.MAX_TEXT_LENGTH;
import static com.zheltoukhov.linguaneo.Constants.WORD_REGEXP_IN_TEXT_REGEXP;

public class TextValidator implements ConstraintValidator<ValidText, String> {
    @Override
    public void initialize(ValidText validText) {}

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.length() <= MAX_TEXT_LENGTH;
    }
}
