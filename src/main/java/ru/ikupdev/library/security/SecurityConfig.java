package ru.ikupdev.library.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.ikupdev.library.security.jwt.JwtConfigurer;
import ru.ikupdev.library.security.jwt.JwtTokenProvider;

/**
 * @author Ilya V. Kupriyanov
 * @version 11.12.2021
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;

    private static final String ADMIN_ENDPOINT = "/api/v1/admin/**";
    private static final String LOGIN_ENDPOINT = "/api/v1/auth/login";
    private static final String API_ENDPOINT = "/api/**";
    private static final String[] SWAGGER_ENDPOINT = new String[]{"/v2/api-docs/**", "/swagger-ui.html"};

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(API_ENDPOINT).permitAll()
                .antMatchers(SWAGGER_ENDPOINT).permitAll()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
//                addFilterBefore(tokenAuthFilter, BasicAuthenticationFilter.class)
//                .antMatcher("/**")
//                .authenticationProvider(tokenAuthenticationProvider)
//                .authorizeRequests()
//                .antMatchers("/users/**").hasAuthority("ADMIN")
//                .antMatchers("/api/user/**").hasAuthority("ADMIN")
//                .antMatchers("/api/**").permitAll()
//                .antMatchers("/").permitAll()
//                .antMatchers("/signUp/**").permitAll()
//                .antMatchers("/login/**").permitAll()
//                .anyRequest().permitAll()
//                .and()
//                    .formLogin()
//                        .usernameParameter("login")
//                        .defaultSuccessUrl("/")
//                        .loginPage("/login")
//                .and()
//                .rememberMe()
//                    .rememberMeParameter("remember-me")
//                .tokenRepository(persistentTokenRepository())
//                .and()
//                .csrf().disable();
    }

//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
//        tokenRepository.setDataSource(dataSource);
//        return tokenRepository;
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//        super.configure(auth);
//    }


}