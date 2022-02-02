package ru.ikupdev.library.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Ilya V. Kupriyanov
 * @version 30.01.2022
 */
@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(SpringFoxConfig.class.getName());
    @Autowired
    protected TypeResolver typeResolver;

    @Bean
    public Docket api() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        List<ResponseMessage> responseMessages = Arrays.asList(
                new ResponseMessageBuilder().code(400).message(BUNDLE.getString("swagger.400.message")).build(),
                new ResponseMessageBuilder().code(403).message(BUNDLE.getString("swagger.403.message")).build(),
                new ResponseMessageBuilder().code(404).message(BUNDLE.getString("swagger.404.message")).build(),
                new ResponseMessageBuilder().code(409).message(BUNDLE.getString("swagger.409.message")).build(),
                new ResponseMessageBuilder().code(500).message(BUNDLE.getString("swagger.500.message")).build());
        docket
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessages)
                .globalResponseMessage(RequestMethod.POST, responseMessages)
                .globalResponseMessage(RequestMethod.DELETE, responseMessages)
                .globalResponseMessage(RequestMethod.PATCH, responseMessages)
                .globalResponseMessage(RequestMethod.PUT, responseMessages)
                .additionalModels(typeResolver.resolve(ru.ikupdev.library.bean.RestResponse.class))
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.ikupdev.library.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.apiInfo());
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Service Library")
                .description("Application for management of books")
                .version("1.0.0")
                .contact(new Contact(
                        "Ilya Kupriyanov",
                        null,
                        "ikupdev@gmail.com"))
                .build();
    }

}

