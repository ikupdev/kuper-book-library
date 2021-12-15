package ru.ikupdev.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Ilya V. Kupriyanov
 * @version 11.12.2021
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                    .antMatchers("/**").permitAll()
                    .and()
                .formLogin()
                .loginPage("/login");

        http.csrf().disable();
//        http.authorizeRequests()
//                .antMatchers("/users/**")
//                .authenticated()
//                .antMatchers("/signUp/**")
//                .permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/login.ftlh");
//        http.csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}