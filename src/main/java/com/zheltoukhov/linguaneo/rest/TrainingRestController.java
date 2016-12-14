package com.zheltoukhov.linguaneo.rest;

import com.zheltoukhov.linguaneo.dto.training.TrainingDto;
import com.zheltoukhov.linguaneo.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Maksim on 11.12.2016.
 */
@RestController
@RequestMapping("/rest/training")
public class TrainingRestController {

    @Autowired
    private TrainingService trainingService;

    @RequestMapping(value = "/{groupId}", method = RequestMethod.GET)
    @ResponseBody
    public TrainingDto getTrainingData(@PathVariable Long groupId){
        return trainingService.getTrainingData(groupId);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public TrainingDto getTrainingData(){
        return trainingService.getTrainingData(null);
    }
}
