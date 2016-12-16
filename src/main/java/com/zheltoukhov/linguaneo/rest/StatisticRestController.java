package com.zheltoukhov.linguaneo.rest;

import com.zheltoukhov.linguaneo.component.StatisticHandler;
import com.zheltoukhov.linguaneo.dto.StatisticDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/statistic")
public class StatisticRestController {

    @Autowired
    private StatisticHandler statisticHandler;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public StatisticDto getTrainingData(){
        return statisticHandler.getStatistics();
    }
}
