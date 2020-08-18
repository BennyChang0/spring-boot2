package com.imooc.diveinspringboot.externalized.configuration.configuration2;

import com.imooc.diveinspringboot.externalized.configuration.domain.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfiguration2 {
    @Bean
    public Dog dog() {
        Dog dog = new Dog();
        dog.setDogAge(0);
        dog.setDogName("alex");

        return dog;
    }
}
