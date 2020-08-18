package com.imooc.spring.boot.web.servlet;

import com.imooc.spring.web.servlet.AsyncServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

@SpringBootApplication
//@ServletComponentScan(basePackages = "com.imooc.spring.web.servlet")
public class SpringBootServletBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootServletBootstrap.class, args);
    }

//    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    public AsyncServlet asyncServlet() {
//        return new AsyncServlet();
//    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new AsyncServlet(), "/async-servlet");
    }

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> {
            CharacterEncodingFilter filter = new CharacterEncodingFilter();
            FilterRegistration.Dynamic registration = servletContext.addFilter("filter", filter);
            registration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/");
        };
    }
}
