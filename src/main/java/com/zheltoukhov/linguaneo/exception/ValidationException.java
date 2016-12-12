package com.zheltoukhov.linguaneo.exception;

import org.springframework.validation.BindingResult;

/**
 * Created by mazh0416 on 12/12/2016.
 */
public class ValidationException extends LinguaneoException {
    private BindingResult bindingResult;
    public ValidationException(String message, BindingResult bindingResult) {
        super(message);
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }

    public void setBindingResult(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }
}
