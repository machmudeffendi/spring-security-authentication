package com.fends.login.controller;

import com.fends.login.model.UserModel;
import com.fends.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping({"/","/login"})
    public String loginPage(Model model){
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model){
        model.addAttribute("user", new UserModel());
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String createNewUser(@Valid UserModel userModel, BindingResult bindingResult, Model model){
        UserModel userExist = userService.findUserByUsername(userModel.getUsername());
        if (userExist != null){
            bindingResult
                    .rejectValue("username", "error.user",
                            "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()){
            model.addAttribute("status","failed");
            model.addAttribute("message", bindingResult.getAllErrors());
        }else{
            userService.saveUserAdmin(userModel);
            model.addAttribute("status","success");
            model.addAttribute("message", "User has been registered successfully");
        }
        return "auth/registration";
    }

    @GetMapping("/admin/home")
    public String homePage(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserModel userModel = userService.findUserByUsername(auth.getName());
        model.addAttribute("userLogin", userModel);
        return "admin/home";
    }
}
