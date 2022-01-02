package ru.ikupdev.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ikupdev.library.model.User;
import ru.ikupdev.library.repository.UserRepository;

import java.util.List;

/**
 * @author Ilya V. Kupriyanov
 * @version 02.01.2022
 */
@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public String getUsers(ModelMap modelMap) {
        List<User> allUsers = userRepository.findAll();
        modelMap.addAttribute("userList", allUsers);
        return "users";
    }

}
