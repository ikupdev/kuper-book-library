package ru.ikupdev.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ikupdev.library.form.UserForm;
import ru.ikupdev.library.service.ISignUpService;

/**
 * @author Ilya V. Kupriyanov
 * @version 15.12.2021
 */
@Controller
public class SignUpController {

    private final ISignUpService signUpService;

    @Autowired
    public SignUpController(ISignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp(UserForm userForm) {
        signUpService.signUp(userForm);
        return "redirect:/login";
    }



}
