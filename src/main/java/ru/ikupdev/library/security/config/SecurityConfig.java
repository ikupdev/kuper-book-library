package ru.ikupdev.library.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ru.ikupdev.library.security.filter.TokenAuthFilter;
import ru.ikupdev.library.security.provider.TokenAuthenticationProvider;

import javax.sql.DataSource;

/**
 * @author Ilya V. Kupriyanov
 * @version 11.12.2021
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Qualifier("userDetailsServiceImpl")
//    @Autowired
//    private UserDetailsService userDetailsService;
//    @Autowired
//    private DataSource dataSource;
//    @Autowired
//    private TokenAuthFilter tokenAuthFilter;
//    @Autowired
//    private TokenAuthenticationProvider tokenAuthenticationProvider;


    private static final String ADMIN_ENDPOINT = "/api/v1/admin/**";
    private static final String LOGIN_ENDPOINT = "/api/v1/auth/login";
    private static final String API_ENDPOINT = "/api/**";
    private static final String[] SWAGGER_ENDPOINT = new String[]{"/v2/api-docs/**","/swagger-ui.html"};


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                    .antMatchers(API_ENDPOINT).permitAll()
                    .antMatchers(SWAGGER_ENDPOINT).permitAll();
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
//                    .tokenRepository(persistentTokenRepository())
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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}