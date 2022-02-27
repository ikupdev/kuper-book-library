package ru.ikupdev.library.service.impl;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.ikupdev.library.dto.UserRequestDto;
import ru.ikupdev.library.dto.UserViewDto;
import ru.ikupdev.library.model.Role;
import ru.ikupdev.library.model.User;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

/**
 * @author Ilya V. Kupriyanov
 * @version 27.02.2022
 */
@ExtendWith(MockitoExtension.class)
class SignUpServiceUnitTest {

    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(SignUpService.class.getName());
    @InjectMocks
    private SignUpService signUpService;
    @Mock
    private UserService userService;
    @Mock
    private RoleService roleService;
    @Mock
    private PasswordEncoder passwordEncoder;

    private final UserRequestDto dto = UserRequestDto.builder()
            .firstName("User")
            .lastName("Userovish")
            .email("user@yandex.ru")
            .login("user1")
            .password("abcd1234")
            .build();

    @Before
    public void setUp() {
        signUpService = new SignUpService(userService, roleService, passwordEncoder);
    }

    @Test
    void whenUserWithNewLoginAndEmail_thenSuccessfulSaved() {
        given(userService.findByLoginOrElseNull(dto.getLogin())).willReturn(null);
        given(userService.findByEmailOrElseNull(dto.getEmail())).willReturn(null);
        given(passwordEncoder.encode(dto.getPassword())).willReturn("encodedPassword");
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_USER");
        given(roleService.findByRoleName("ROLE_USER")).willReturn(role);
        given(userService.saveNew(Mockito.any())).willReturn(new UserViewDto(1L, dto.getLogin()));
        signUpService.signUp(dto);
    }

    @Test
    void whenUserWithLoginExist_thenThrowIllegalArgumentException() {
        given(userService.findByLoginOrElseNull(dto.getLogin())).willReturn(User.builder().login(dto.getLogin()).build());
        Throwable ex = assertThrows(IllegalArgumentException.class,() -> signUpService.signUp(dto));
        assertEquals(String.format(BUNDLE.getString("signup.exist.login"), dto.getLogin()), ex.getMessage());
    }

    @Test
    void whenUserWithEmailExist_thenThrowIllegalArgumentException() {
        given(userService.findByLoginOrElseNull(dto.getLogin())).willReturn(null);
        given(userService.findByEmailOrElseNull(dto.getEmail())).willReturn(User.builder().email(dto.getEmail()).build());
        Throwable ex = assertThrows(IllegalArgumentException.class,() -> signUpService.signUp(dto));
        assertEquals(String.format(BUNDLE.getString("signup.exist.email"), dto.getEmail()), ex.getMessage());
    }

}