package com.imooc.diveinspringboot.externalized.configuration.boostrap;

import com.imooc.diveinspringboot.externalized.configuration.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring Xml 配置占位符引导类
 */
public class SpringXmlConfigPlaceholderBootstrap {

    public static void main(String[] args) {

        String[] locations = {"META-INF/spring/spring-context.xml", "META-INF/spring/user-context.xml"};

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(locations);

        User user = context.getBean("user", User.class);
        System.err.println(user);

        context.close();
    }
}
