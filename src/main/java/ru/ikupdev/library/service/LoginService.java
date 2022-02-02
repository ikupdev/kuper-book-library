package ru.ikupdev.library.service;

/**
 * @author Ilya V. Kupriyanov
 * @version 02.01.2022
 */
//@Component
//public class LoginService implements ILoginService {
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public TokenDto login(LoginForm loginForm) {
//        Optional<User> userCandidate = userRepository.findByUsername(loginForm.getLogin());
//        if (userCandidate.isPresent()) {
//            User user = userCandidate.get();
//            if (passwordEncoder.matches(loginForm.getPassword(), user.getPassword())) {
//                Token token = Token.builder()
//                        .user(user)
//                        .tokenValue(RandomStringUtils.random(10, true, true))
//                        .build();
////                tokenRepository.save(token);
//                return TokenDto.from(token);
//            } else {
//                throw new IllegalArgumentException("Incorrect password!");
//            }
//        } else {
//            throw new IllegalArgumentException("User not found!");
//        }
//    }
//}
