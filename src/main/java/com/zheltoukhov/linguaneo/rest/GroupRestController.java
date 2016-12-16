package com.zheltoukhov.linguaneo.rest;

import com.zheltoukhov.linguaneo.dto.group.CreateGroupDto;
import com.zheltoukhov.linguaneo.entity.WordsGroup;
import com.zheltoukhov.linguaneo.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

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

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public WordsGroup create(@RequestBody CreateGroupDto group) {
        return groupService.create(group.getName());
    }

    @RequestMapping(value = "/{groupId}", method = RequestMethod.DELETE)
    @ResponseBody
    @ResponseStatus(NO_CONTENT)
    public void delete(Long groupId) {
        groupService.delete(groupId);
    }
}
