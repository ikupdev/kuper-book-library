package ru.ikupdev.library.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ikupdev.library.model.User;
import ru.ikupdev.library.service.impl.UserService;
import ru.ikupdev.library.transfer.UserTO;

import java.util.List;

import static ru.ikupdev.library.config.AppConstants.API_V1_PATH;

/**
 * @author Ilya V. Kupriyanov
 * @version 02.01.2022
 */
@RestController
@RequestMapping(API_V1_PATH + "/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/list")
    public List<User> getUsers() {
        return userService.getAll();
    }

    @GetMapping("/{user-id}")
    public User getUser(@PathVariable("user-id") Long userId) {
        return userService.findById(userId);
    }

//    @PostMapping
//    public ResponseEntity<Object> addUser(@RequestBody UserForm userForm) {
//        if (userService.checkIsExistByLogin(userForm.getLogin())) throw new IllegalArgumentException("User with this login exist!");
//        signUpService.signUp(userForm);
//        return ResponseEntity.ok().build();
//    }
//
//    @PutMapping("/{user-id}/update")
//    public ResponseEntity<Object> updateUser(@PathVariable("user-id") Long userId,
//                                             @RequestBody UserTO userTO) {
//        userService.updateUser(userId, userTO);
//        return ResponseEntity.ok().build();
//    }

}
