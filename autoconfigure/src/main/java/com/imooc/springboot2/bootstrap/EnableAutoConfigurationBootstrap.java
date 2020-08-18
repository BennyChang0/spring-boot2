package com.imooc.springboot2.bootstrap;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@EnableAutoConfiguration
// META-INF/spring.factories -> HelloWorldAutoConfiguration -> @ConditionalOnSystemProperty -> OnSystemPropertyCondition
//                                                          -> @EnableHelloWorld            -> HelloWorldImportSelector -> HelloWorld
// 1.激活自动装配 @EnableAutoConfiguration
// 2.实现自动装配 xxxAutoConfiguration
// 3.配置自动装配 META-INF/spring.factories SpringFactoriesLoader
public class EnableAutoConfigurationBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(EnableAutoConfigurationBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        String helloWorld = context.getBean("helloWorld", String.class);
        System.out.println("helloWorld Bean: " + helloWorld);

        context.close();
    }
}
