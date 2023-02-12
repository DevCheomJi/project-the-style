package com.susu.projectthestyle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class ProjectTheStyleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectTheStyleApplication.class, args);
    }

}
