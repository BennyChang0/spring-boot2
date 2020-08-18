package com.imooc.diveinspringboot.externalized.configuration.boostrap;

import com.imooc.diveinspringboot.externalized.configuration.configuration.CustomConfiguration;
import com.imooc.diveinspringboot.externalized.configuration.domain.User;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

//@PropertySource("classpath:application.properties")
@ImportResource("classpath:META-INF/spring/user-context.xml")
//@EnableAutoConfiguration
@ComponentScan({"com.imooc.diveinspringboot.externalized.configuration"})
//@Import(CustomConfiguration.class)
//@EnableConfigurationProperties
public class XmlPlaceholderExternalizedConfigurationBootstrap {
    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(XmlPlaceholderExternalizedConfigurationBootstrap.class)
                        .web(WebApplicationType.NONE)
                        .run(args);

        System.out.println(context.getEnvironment().getProperty("user.id"));
        System.out.println(context.getEnvironment().getProperty("user.name"));

        User user = context.getBean("user", User.class);
        System.err.println(user);

        System.err.printf("System.getProperty(\"%s\") : %s \n", "user.name", System.getProperty("user.name"));

        context.close();
    }
}
