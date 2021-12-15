package ru.ikupdev.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.ikupdev.library")
public class ServiceLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceLibraryApplication.class, args);
    }

}

