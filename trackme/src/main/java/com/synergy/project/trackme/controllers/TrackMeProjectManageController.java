package com.synergy.project.trackme.controllers;

import com.synergy.project.trackme.Dto.ContactCreationDto;
import com.synergy.project.trackme.model.Contact;
import com.synergy.project.trackme.model.Project;
import com.synergy.project.trackme.repository.TrackMeProjectRepository;
import com.synergy.project.trackme.service.TrackMeProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class TrackMeProjectManageController {

    @Autowired
    private TrackMeProjectService trackMEProjectService;

    @GetMapping("/manageproject/{id}")
    public String manageProject(@PathVariable int id , Model model){

        ContactCreationDto contactCreationDto = new ContactCreationDto();

        for (int i = 1; i <= 10; i++) {
            contactCreationDto.addContact(new Contact());
        }

        model.addAttribute("form", contactCreationDto );

        Project project = trackMEProjectService.getProject(id);

        model.addAttribute("project", project);

        return "manageproject";
    }

    @PostMapping("/manageproject")
    public String addProject(@Valid Project project){
        trackMEProjectService.addProject(project);
        return "projectlist";
    }

    @PutMapping ("/manageproject/{id}")
    public String editProject(@Valid Project project, @ModelAttribute ContactCreationDto contactCreationDto){
        trackMEProjectService.editProject(project, contactCreationDto);
        return "projectlist";
    }

    @DeleteMapping("/manageproject/{id}")
    public String deleteProject(@PathVariable int id){

        trackMEProjectService.deleteProject(id);
        return "projectlist";
    }

}
