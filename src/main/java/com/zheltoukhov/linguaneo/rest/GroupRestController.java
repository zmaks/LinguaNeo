package com.zheltoukhov.linguaneo.rest;

import com.zheltoukhov.linguaneo.entity.WordsGroup;
import com.zheltoukhov.linguaneo.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Maksim on 10.12.2016.
 */
@RestController
@RequestMapping("/rest/groups")
public class GroupRestController {

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<WordsGroup> getAll() {
        return groupService.getAllGroups();
    }
}
