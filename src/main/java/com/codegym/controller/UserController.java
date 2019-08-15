package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String showForm(Model model){
        model.addAttribute("user",new User());
        return "index";
    }

    @PostMapping("/user")
    public String checkValidation(@Valid @ModelAttribute ("user") User user, Model model, BindingResult bindingResult){
        new User().validate(user,bindingResult);
        if(bindingResult.hasFieldErrors()){
            return "index";
        }
        else{
            userService.save(user);
            model.addAttribute("user",user);
            return "result";
        }
    }
}
