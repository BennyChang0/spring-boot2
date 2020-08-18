package com.imooc.diveinspringboot.externalized.configuration.boostrap;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

@EnableAutoConfiguration
public class EnvironmentBootstrap implements EnvironmentAware, BeanFactoryAware {

    private Environment environment;

    @Autowired
    public EnvironmentBootstrap(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (beanFactory.getBean("environment", Environment.class) != environment) {
            throw new IllegalStateException();
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        if (this.environment != environment) {
            throw new IllegalStateException();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(EnvironmentBootstrap.class, args);

        /**
         * 1. @Autowired 注解
         * 2. BeanFactoryAware 接口
         * 3. EnvironmentAware 接口
         *
         * 三种方式顺序获取，Environment是同一个对象
         */
    }
}
