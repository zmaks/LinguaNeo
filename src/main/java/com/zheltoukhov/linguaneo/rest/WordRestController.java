package com.zheltoukhov.linguaneo.rest;

import com.zheltoukhov.linguaneo.dto.translation.TranslateWordRequestDto;
import com.zheltoukhov.linguaneo.dto.translation.TranslationWordDto;
import com.zheltoukhov.linguaneo.dto.word.UpdateGroupDto;
import com.zheltoukhov.linguaneo.dto.word.WordDto;
import com.zheltoukhov.linguaneo.entity.Word;
import com.zheltoukhov.linguaneo.exception.ValidationException;
import com.zheltoukhov.linguaneo.service.WordService;
import com.zheltoukhov.linguaneo.validation.annotation.ValidWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.zheltoukhov.linguaneo.Constants.Messages.WORD_VALIDATION_MESSAGE;
import static org.springframework.http.HttpStatus.NO_CONTENT;

/**
 * Created by Maksim on 08.12.2016.
 */
@RestController
@RequestMapping("/rest/words")
public class WordRestController {

    @Autowired
    private WordService wordService;

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

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id) {
        wordService.delete(id);
    }

    @RequestMapping(value = "/translate/", method = RequestMethod.POST)
    @ResponseBody
    public TranslationWordDto translateWord(@RequestBody @Valid TranslateWordRequestDto word, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            throw new ValidationException(WORD_VALIDATION_MESSAGE, bindingResult);
        }
        return wordService.translateWord(word.getWord() );
    }

    @RequestMapping(value = "/group/", method = RequestMethod.PUT)
    @ResponseBody
    public Word translateWord(@RequestBody UpdateGroupDto updateGroupDto) {
        return wordService.updateGroup(updateGroupDto);
    }
}
