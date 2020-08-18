package com.imooc.diveinspringboot.externalized.configuration.configuration;

import com.imooc.diveinspringboot.externalized.configuration.domain.Person;
import com.imooc.diveinspringboot.externalized.configuration.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfiguration {

    @Bean
    public User user() {
        User u = new User();
        u.setId(1L);
        u.setName("user");
        u.setAge(22);
        u.setDesc("xxx");
        return u;
    }

    @Bean
    public Person person(@Value("${person.id}") Long id, @Value("${person.name}") String name,
                         @Value("${person.age:${p.age:1999}}") Integer age, @Value("${person.desc:caonima2019}") String desc) {
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        person.setAge(age);
        person.setDesc(desc);
        return person;
    }
}
