package ru.ikupdev.library.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
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

    private static final String ADMIN_V1_ENDPOINT = "/api/v1/admin/**";
    private static final String API_V1_ENDPOINT = "/api/v1/**";
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
                .antMatchers(SWAGGER_ENDPOINT).permitAll()
                .antMatchers(API_V1_ENDPOINT).permitAll()
                .antMatchers(ADMIN_V1_ENDPOINT).hasAuthority("ADMIN")
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}