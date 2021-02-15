package com.synergy.project.trackme.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrackMeMainController {

    @GetMapping("/mainpage")
    public String mainPage(){

        return "mainpage";
    }
}
