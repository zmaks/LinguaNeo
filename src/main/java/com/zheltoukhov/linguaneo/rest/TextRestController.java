package com.zheltoukhov.linguaneo.rest;

import com.zheltoukhov.linguaneo.dto.TranslationWordDto;
import com.zheltoukhov.linguaneo.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<TranslationWordDto> translateText(@PathVariable String text) {
        return textService.parseAndTranslateText(text);
    }
}
