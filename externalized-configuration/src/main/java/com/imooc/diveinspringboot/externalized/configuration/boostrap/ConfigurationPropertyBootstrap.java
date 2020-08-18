package com.imooc.diveinspringboot.externalized.configuration.boostrap;

import com.imooc.diveinspringboot.externalized.configuration.domain.Person;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@EnableAutoConfiguration
@EnableConfigurationProperties
public class ConfigurationPropertyBootstrap {

    @Bean
    @ConfigurationProperties(prefix = "person")
    @ConditionalOnProperty(prefix = "person", name = "city.post_code", matchIfMissing = false, havingValue = "200001")
    public Person person () {
        return new Person();
    }

    public static void main(String[] args) {

        /**
         * @ConfigurationProperties 类级别
         * @ConfigurationProperties @Bean 方法级别
         * @ConfigurationProperties 嵌套属性
         * @EnableConfigurationProperties(Person.class) 指定类
         *
         * @ConditionalOnProperty prefix name 要与application.properties完全一致，在环境变量里面会允许松散绑定
         *
         * Java System Properties
         * -Duser.city.post_code = 200001
         *
         * OS Environment Variables
         * PERSON_CITY_POST_CODE = 200001
         *
         * application.properties
         * person.city.post_code = 200001
         *
         */

        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(ConfigurationPropertyBootstrap.class)
                        .web(WebApplicationType.NONE)
                        .run(args);

        Person person = context.getBean("person", Person.class);
        System.err.println("用户对象: " + person);

        context.close();
    }
}
