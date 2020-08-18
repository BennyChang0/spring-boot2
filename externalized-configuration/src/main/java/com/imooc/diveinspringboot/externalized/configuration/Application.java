package com.imooc.diveinspringboot.externalized.configuration;

import com.imooc.diveinspringboot.externalized.configuration.boostrap.XmlPlaceholderExternalizedConfigurationBootstrap;
import com.imooc.diveinspringboot.externalized.configuration.configuration.CustomConfiguration;
import com.imooc.diveinspringboot.externalized.configuration.domain.Dog;
import com.imooc.diveinspringboot.externalized.configuration.domain.Person;
import com.imooc.diveinspringboot.externalized.configuration.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@EnableAutoConfiguration // 1.注册扫描的包，2.加载spring.factories文件中的自动装配的类
//@Import(CustomConfiguration.class) // 导入@Configuration注解配置@Bean
//@ImportResource("classpath:META-INF/spring/user-context.xml") // 导入xml文件中的Bean
// 扫描指定的包，加载@Configuration配置@Bean
@ComponentScan({"com.imooc.diveinspringboot.externalized.configuration.configuration", "com.imooc.diveinspringboot.externalized.configuration.configuration2"})
public class Application {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        User user = context.getBean("user", User.class);
        System.out.println(user);

        Person person = context.getBean("person", Person.class);
        System.out.println(person);

        Dog dog = context.getBean("dog", Dog.class);
        System.out.println(dog);

    }
}
