package ru.ikupdev.library.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ikupdev.library.security.details.UserDetailsImpl;
import ru.ikupdev.library.transfer.UserView;

/**
 * @author Ilya V. Kupriyanov
 * @version 15.12.2021
 */
@Controller
public class ProfileController {

    @GetMapping("/")
    public String getProfilePage(ModelMap model, Authentication authentication) {
        if (authentication == null) return "redirect:/login";
        UserDetailsImpl details = (UserDetailsImpl) authentication.getPrincipal();
        UserView userView = UserView.from(details.getUser());
        model.addAttribute("user", userView);
        return "profile";
    }

}
