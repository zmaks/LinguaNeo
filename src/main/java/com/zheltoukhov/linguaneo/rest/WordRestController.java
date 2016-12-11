package com.zheltoukhov.linguaneo.rest;

import com.zheltoukhov.linguaneo.dto.TranslationWordDto;
import com.zheltoukhov.linguaneo.dto.WordDto;
import com.zheltoukhov.linguaneo.entity.Word;
import com.zheltoukhov.linguaneo.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Maksim on 08.12.2016.
 */
@RestController
@RequestMapping("/rest/words")
public class WordRestController {

    @Autowired
    WordService wordService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<Word> getAll() {
        return wordService.getAllWords();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Word create(@RequestBody WordDto word) {
        return wordService.create(word);
    }

    @RequestMapping(value = "translate/{word}", method = RequestMethod.GET)
    @ResponseBody
    public TranslationWordDto translateWord(@PathVariable String word) {
        return wordService.translateWord(word);
    }
}
