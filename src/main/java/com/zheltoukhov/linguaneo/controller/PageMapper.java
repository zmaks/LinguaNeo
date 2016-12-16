package com.zheltoukhov.linguaneo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageMapper {
    @RequestMapping("")
    public String getHomepage() {
        return "index";
    }
}
