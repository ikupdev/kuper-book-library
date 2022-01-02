package ru.ikupdev.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ikupdev.library.form.UserForm;
import ru.ikupdev.library.model.User;
import ru.ikupdev.library.repository.UserRepository;
import ru.ikupdev.library.type.Role;
import ru.ikupdev.library.type.State;

/**
 * @author Ilya V. Kupriyanov
 * @version 15.12.2021
 */
@Service
public class SignUpService implements ISignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SignUpService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signUp(UserForm userForm) {
        String hashPassword = passwordEncoder.encode(userForm.getPassword());
        User user = User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .login(userForm.getLogin())
                .hashPassword(hashPassword)
                .role(Role.USER)
                .state(State.ACTIVE)
                .build();
        userRepository.save(user);
    }


}
