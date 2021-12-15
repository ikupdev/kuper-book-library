package ru.ikupdev.library.config;

import com.fasterxml.classmate.TypeResolver;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UriComponentsBuilder;
import ru.ikupdev.library.controller.BookController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.json.Json;
import springfox.documentation.spring.web.paths.DefaultPathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import static springfox.documentation.spring.web.paths.Paths.removeAdjacentForwardSlashes;


//@Configuration
//@EnableSwagger2WebMvc
//public class SwaggerConfig implements WebMvcConfigurer {
//    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(SwaggerConfig.class.getName());
//    @Autowired
//    private GsonBuilder gsonBuilder;
//    @Autowired
//    protected TypeResolver typeResolver;
//
//    @Value("${server.servlet.context-path}")
//    private String contextPath;
//
//    //override'им bpm-core-бин, для регистрации springfox json-адаптера
//    @Bean
//    public Gson gson() {
//        return gsonBuilder
//                .registerTypeAdapter(Json.class, new SpringfoxJsonSerializer())
//                .create();
//    }
//
//    private static class SpringfoxJsonSerializer implements JsonSerializer<Json> {
//        @Override
//        public JsonElement serialize(Json json, Type type, JsonSerializationContext jsonSerializationContext) {
//            JsonParser parser = new JsonParser();
//            return parser.parse(json.value());
//        }
//    }
//
//    @Bean
//    public Docket api() {
//        Docket docket = new Docket(DocumentationType.SWAGGER_2);
//
//        List<ResponseMessage> responseMessages = Arrays.asList(
//                new ResponseMessageBuilder().code(400).message(BUNDLE.getString("swagger.400.message")).build(),
//                new ResponseMessageBuilder().code(403).message(BUNDLE.getString("swagger.403.message")).build(),
//                new ResponseMessageBuilder().code(404).message(BUNDLE.getString("swagger.404.message")).build(),
//                new ResponseMessageBuilder().code(409).message(BUNDLE.getString("swagger.409.message")).build(),
//                new ResponseMessageBuilder().code(500).message(BUNDLE.getString("swagger.500.message")).build());
//
//        return (docket)
//                .useDefaultResponseMessages(false)
//                .globalResponseMessage(RequestMethod.GET, responseMessages)
//                .globalResponseMessage(RequestMethod.POST, responseMessages)
//                .globalResponseMessage(RequestMethod.DELETE, responseMessages)
//                .globalResponseMessage(RequestMethod.PATCH, responseMessages)
//                .globalResponseMessage(RequestMethod.PUT, responseMessages)
////                .additionalModels(
////                        typeResolver.resolve(RestResponse.class),
////                )
//                .pathProvider(new CustomPathProvider())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage(this.getBaseControllersPackage()))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(this.apiInfo());
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
//
//    protected ApiInfo apiInfo() {
//        return new ApiInfo(
//                BUNDLE.getString("swagger.app.name"),
//                BUNDLE.getString("swagger.app.description"),
//                BUNDLE.getString("swagger.app.version"),
//                null,
//                null,
//                null,
//                null,
//                new ArrayList<>());
//    }
//
//    public String getBaseControllersPackage() {
//        return BookController.class.getPackage().getName();
//    }
//
//    //NOTE: удаляет задвоение /context-path при выполнении запроса в "Try it out"
//    private class CustomPathProvider extends DefaultPathProvider {
//        @Override
//        public String getOperationPath(String operationPath) {
//            if (operationPath.startsWith(contextPath)) {
//                operationPath = operationPath.substring(contextPath.length());
//            }
//            return removeAdjacentForwardSlashes(UriComponentsBuilder.newInstance().replacePath(operationPath)
//                    .build().toString());
//        }
//    }
//}
