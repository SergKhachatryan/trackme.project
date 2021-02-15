package com.synergy.project.trackme.controllers;

import com.synergy.project.trackme.model.Project;
import com.synergy.project.trackme.repository.TrackMeProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedList;
import java.util.List;

@Controller
public class TrackMeProjectListController {

    @Autowired
    private TrackMeProjectRepository projectRepository;

    @GetMapping("/userprofile")
    public String projectList(Model model){

        List<Project> projects = projectRepository.findAll();

        model.addAttribute("projects", projects);

        return "projectlist";
    }

}
