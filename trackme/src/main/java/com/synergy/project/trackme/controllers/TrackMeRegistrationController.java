package com.synergy.project.trackme.controllers;

import com.synergy.project.trackme.model.User;
import com.synergy.project.trackme.service.TrackMeUserService;
import com.synergy.project.trackme.util.TrackMeControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Map;

@Controller
public class TrackMeRegistrationController {

    @Autowired
    TrackMeUserService trackMeUserService;

    @GetMapping("/registration")
    public String registration(){

        return "registration";
        }

    @PostMapping("/registration")
    public String addUser(@RequestParam("passwordConfirmation") String passwordConfirm, @Valid User user, BindingResult bindingResult, Model model) {

        if(passwordConfirm == null){
            model.addAttribute("passwordError" , "passwords confirmation can't be empty");
        }

        if(user.getPassword() != null && user.getPassword().equals(passwordConfirm)){
            model.addAttribute("passwordError" , "passwords not match");
        }

        if(passwordConfirm == null || bindingResult.hasErrors()){
            System.out.println(144);
            Map<String, String> errors = TrackMeControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }

        if(!trackMeUserService.tryAddUser(user)){
            System.out.println(14);
            model.addAttribute("usernameError", "User exists!");
            return "registration";
        }

             return "login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){

        if (trackMeUserService.tryActivateUser(code)){
            model.addAttribute("message", "User successfully activated");
        }

        else{
            model.addAttribute("massage", "Activation code is not found");
        }

        return "login";
    }

}
