package com.zheltoukhov.linguaneo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Maksim on 10.12.2016.
 */
@Controller
@RequestMapping("/")
public class PageMapper {
    @RequestMapping("")
    public String getHomepage() {
        return "index";
    }
}
