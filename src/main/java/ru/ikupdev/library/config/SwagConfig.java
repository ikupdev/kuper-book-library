package ru.ikupdev.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author Ilya V. Kupriyanov
 * @version 11.12.2021
 */
//@Configuration
//@EnableSwagger2WebMvc
//public class SwagConfig {
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }

//    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin() // Customize yourself to write a login.ftlh page
//                .loginPage("/login.ftlh.html") // Login page settings
//                .loginProcessingUrl("/user/login.ftlh") // Login Access Path
//                .defaultSuccessUrl("/test/index").permitAll() // Sign in successful jump path
//                .and().authorizeRequests()
//                .antMatchers("/","/test/hello","/login.ftlh.html").permitAll() // Set which paths can be accessed directly, no authentication
//                .anyRequest().authenticated()
//                .and().csrf().disable(); // Turn off CSRF protection
//    }
//}
