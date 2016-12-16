package com.zheltoukhov.linguaneo.validation;

import com.zheltoukhov.linguaneo.validation.annotation.ValidGroupName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.regex.Pattern;

/**
 * Created by Maksim on 16.12.2016.
 */
public class GroupNameValidator implements ConstraintValidator<ValidGroupName, String> {
    @Override
    public void initialize(ValidGroupName validGroupName) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.length()>0&& s.length()<=15;
    }
}
