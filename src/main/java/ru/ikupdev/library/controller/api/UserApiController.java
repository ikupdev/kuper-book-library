package ru.ikupdev.library.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ikupdev.library.form.UserForm;
import ru.ikupdev.library.model.User;
import ru.ikupdev.library.service.SignUpService;
import ru.ikupdev.library.service.UserService;

import java.util.List;
import java.util.Optional;

/**
 * @author Ilya V. Kupriyanov
 * @version 02.01.2022
 */
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserService userService;
    private final SignUpService signUpService;

    public UserApiController(UserService userService, SignUpService signUpService) {
        this.userService = userService;
        this.signUpService = signUpService;
    }

    @GetMapping("/list")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/{user-id}")
    public User getUser(@PathVariable("user-id") Long userId) {
        Optional<User> optionalUser = userService.findById(userId);
        if (optionalUser.isPresent()) return optionalUser.get();
        else throw new IllegalArgumentException("User not found");
    }

    @PostMapping
    public ResponseEntity<Object> addUser(@RequestBody UserForm userForm) {
        signUpService.signUp(userForm);
        return ResponseEntity.ok().build();
    }

}
