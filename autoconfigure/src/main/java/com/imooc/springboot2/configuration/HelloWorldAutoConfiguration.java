package com.imooc.springboot2.configuration;

import com.imooc.springboot2.annotation.EnableHelloWorld;
import com.imooc.springboot2.condition.ConditionalOnSystemProperty;
import org.springframework.boot.SpringBootConfiguration;

@SpringBootConfiguration
@EnableHelloWorld
@ConditionalOnSystemProperty(name = "user.name", value = "benny")
public class HelloWorldAutoConfiguration {

}
