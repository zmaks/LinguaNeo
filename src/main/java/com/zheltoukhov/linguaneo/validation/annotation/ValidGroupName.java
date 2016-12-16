package com.zheltoukhov.linguaneo.validation.annotation;

import com.zheltoukhov.linguaneo.validation.GroupNameValidator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy=GroupNameValidator.class)
public @interface ValidGroupName {
    String message();

    Class[] groups() default {};

    Class[] payload() default {};
}
