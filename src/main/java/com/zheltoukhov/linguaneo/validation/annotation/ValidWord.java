package com.zheltoukhov.linguaneo.validation.annotation;

import com.zheltoukhov.linguaneo.validation.WordValidator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by mazh0416 on 12/12/2016.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy=WordValidator.class)
public @interface ValidWord {
    String message();

    Class[] groups() default {};

    Class[] payload() default {};
}
