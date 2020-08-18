package com.imooc.diveinspringboot.externalized.configuration.boostrap;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;

@EnableAutoConfiguration
@Configuration
// @PropertySource
@PropertySource(name = "from classpath:META-INF/default.properties", value = "classpath:META-INF/default.properties")
public class ExtendPropertySourcesBootstrap {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new SpringApplicationBuilder(ExtendPropertySourcesBootstrap.class)
                .web(WebApplicationType.NONE)   // 非web应用
                .properties("user.id=99")       // Default Properties
                .run(of("--user.id=88"));// commandLine arguments

        // 获取 Environment
        ConfigurableEnvironment environment = context.getEnvironment();
        System.err.printf("用户id : %d\n", environment.getProperty("user.id", Long.class));

        environment.getPropertySources().forEach(propertySource -> {
            System.err.printf("PropertySource[名称:%s] :%s\n", propertySource.getName(), propertySource);
        });

        context.close();
    }

    public static <T> T[] of(T... args) {
        return args;
    }
}
