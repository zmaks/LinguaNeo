package com.zheltoukhov.linguaneo.rest;

import com.zheltoukhov.linguaneo.dto.translation.TranslateTextRequestDto;
import com.zheltoukhov.linguaneo.dto.translation.TranslateWordRequestDto;
import com.zheltoukhov.linguaneo.dto.translation.TranslationWordDto;
import com.zheltoukhov.linguaneo.exception.ValidationException;
import com.zheltoukhov.linguaneo.service.TextService;
import com.zheltoukhov.linguaneo.validation.annotation.ValidText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

import static com.zheltoukhov.linguaneo.Constants.Messages.TEXT_VALIDATION_MESSAGE;

/**
 * Created by Maksim on 10.12.2016.
 */
@RestController
@RequestMapping("/rest/text")
public class TextRestController {

    @Autowired
    private TextService textService;

    @RequestMapping(value = "/translate/", method = RequestMethod.POST)
    @ResponseBody
    public Set<TranslationWordDto> translateText(@RequestBody @Valid TranslateTextRequestDto text, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            throw new ValidationException(TEXT_VALIDATION_MESSAGE, bindingResult);
        }
        return textService.parseAndTranslateText(text.getText());
    }
}
