package com.zheltoukhov.linguaneo.rest;

import com.zheltoukhov.linguaneo.dto.TranslationWordDto;
import com.zheltoukhov.linguaneo.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Maksim on 08.12.2016.
 */
@RestController
@RequestMapping("/rest/words")
public class WordRestController {

    @Autowired
    WordService wordService;

    @RequestMapping(value = "/{word}", method = RequestMethod.GET)
    @ResponseBody
    public TranslationWordDto create(@PathVariable String word) {
        return wordService.translateWord(word);
    }

    @RequestMapping(value = "translate/{word}", method = RequestMethod.GET)
    @ResponseBody
    public TranslationWordDto translateWord(@PathVariable String word) {
        return wordService.translateWord(word);
    }
}
