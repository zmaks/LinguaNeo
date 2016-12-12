package com.zheltoukhov.linguaneo.rest;

import com.zheltoukhov.linguaneo.dto.TranslationWordDto;
import com.zheltoukhov.linguaneo.dto.UpdateGroupDto;
import com.zheltoukhov.linguaneo.dto.WordDto;
import com.zheltoukhov.linguaneo.entity.Word;
import com.zheltoukhov.linguaneo.exception.ValidationException;
import com.zheltoukhov.linguaneo.service.WordService;
import com.zheltoukhov.linguaneo.validation.annotation.ValidWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.zheltoukhov.linguaneo.Constants.Messages.WORD_VALIDATION_MESSAGE;

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

    @RequestMapping(value = "/translate/{word}", method = RequestMethod.GET)
    @ResponseBody
    public TranslationWordDto translateWord(@PathVariable @ValidWord String word, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            throw new ValidationException(WORD_VALIDATION_MESSAGE, bindingResult);
        }
        return wordService.translateWord(word);
    }

    @RequestMapping(value = "/setgroup/", method = RequestMethod.PUT)
    @ResponseBody
    public Word translateWord(@RequestBody UpdateGroupDto updateGroupDto) {
        return wordService.updateGroup(updateGroupDto);
    }
}
