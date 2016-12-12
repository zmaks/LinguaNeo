package com.zheltoukhov.linguaneo.controller;

import com.zheltoukhov.linguaneo.dto.ErrorDto;
import com.zheltoukhov.linguaneo.exception.LinguaneoException;
import com.zheltoukhov.linguaneo.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Created by mazh0416 on 12/12/2016.
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    public List<ErrorDto> validationErrorHandler(ValidationException e) {
        List<ErrorDto> errorDtoList = new ArrayList<ErrorDto>();
        for (FieldError error : e.getBindingResult().getFieldErrors()){
            errorDtoList.add(new ErrorDto(error.getField(), error.getDefaultMessage()));
        }
        return errorDtoList;
        /*return e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map((x) -> new ErrorDto(x.getField(), x.getDefaultMessage()))
                .collect(toList());
*/
    }

    @ExceptionHandler(LinguaneoException.class)
    public ResponseEntity<ErrorDto> handle(LinguaneoException ex) {
        ErrorDto error = new ErrorDto(HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage());
        return new ResponseEntity<ErrorDto>(error, HttpStatus.BAD_REQUEST);
    }
}
