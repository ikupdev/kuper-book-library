package ru.ikupdev.library.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ilya V. Kupriyanov
 * @version 12/05/2021
 */
@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public String getLoginPage(Authentication authentication, ModelMap model, HttpServletRequest request) {
        if (authentication != null) return "redirect:/";
        if (request.getParameterMap().containsKey("error")) {
            model.addAttribute("error",true);
        }
        return "login";
    }

}
