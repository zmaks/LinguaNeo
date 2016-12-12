package com.zheltoukhov.linguaneo.rest;

import com.zheltoukhov.linguaneo.dto.TranslationWordDto;
import com.zheltoukhov.linguaneo.exception.ValidationException;
import com.zheltoukhov.linguaneo.service.TextService;
import com.zheltoukhov.linguaneo.validation.annotation.ValidText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.zheltoukhov.linguaneo.Constants.Messages.TEXT_VALIDATION_MESSAGE;
import static com.zheltoukhov.linguaneo.Constants.Messages.WORD_VALIDATION_MESSAGE;

/**
 * Created by Maksim on 10.12.2016.
 */
@RestController
@RequestMapping("/rest/text")
public class TextRestController {

    @Autowired
    private TextService textService;

    @RequestMapping(value = "translate/{text}", method = RequestMethod.GET)
    @ResponseBody
    public List<TranslationWordDto> translateText(@PathVariable @ValidText String text, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            throw new ValidationException(TEXT_VALIDATION_MESSAGE, bindingResult);
        }
        return textService.parseAndTranslateText(text);
    }
}
