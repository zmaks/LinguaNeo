package com.zheltoukhov.linguaneo.controller;

import com.zheltoukhov.linguaneo.dto.ErrorDto;
import com.zheltoukhov.linguaneo.exception.LinguaneoException;
import com.zheltoukhov.linguaneo.exception.ValidationException;
import com.zheltoukhov.linguaneo.translator.exception.TranslatorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

import static com.zheltoukhov.linguaneo.Constants.Messages.EXCEPTION_MESSAGE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    public ErrorDto validationErrorHandler(ValidationException e) {
        FieldError error = e.getBindingResult().getFieldError();
        return new ErrorDto(error.getField(), error.getDefaultMessage());
    }

    @ExceptionHandler(LinguaneoException.class)
    public ResponseEntity<ErrorDto> handleLinguaException(LinguaneoException ex) {
        ErrorDto error = new ErrorDto(HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage());
        return new ResponseEntity<ErrorDto>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TranslatorException.class)
    public ResponseEntity<ErrorDto> handleTranslatorException(TranslatorException ex) {
        return internalErrorHandler(ex);
    }

    private ResponseEntity<ErrorDto> internalErrorHandler(Exception ex){
        ErrorDto error = new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage());
        return new ResponseEntity<ErrorDto>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(Exception ex) {
        ErrorDto error = new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), EXCEPTION_MESSAGE);
        return new ResponseEntity<ErrorDto>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
