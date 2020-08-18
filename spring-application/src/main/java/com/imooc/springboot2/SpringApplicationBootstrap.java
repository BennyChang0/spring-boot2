package com.imooc.springboot2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashSet;
import java.util.Set;

public class SpringApplicationBootstrap {
    public static void main(String[] args) {
//        SpringApplication.run(ApplicationConfiguration.class, args);

//        new SpringApplicationBuilder(ApplicationConfiguration.class).run(args);

        // BeanDefinitionLoader

        Set<String> sources = new HashSet<>();
        sources.add(ApplicationConfiguration.class.getName());
        SpringApplication springApplication = new SpringApplication();
        springApplication.setSources(sources);
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        ConfigurableApplicationContext context = springApplication.run(args);
        ApplicationConfiguration applicationConfiguration = context.getBean(ApplicationConfiguration.class);
        System.out.println("Bean ApplicationConfiguration: " + applicationConfiguration);

    }

    @SpringBootApplication
    public static class ApplicationConfiguration {

    }
}
