package com.imooc.springboot2.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Import(HelloWorldConfiguration.class)// 不灵活，没有弹性
@Import(HelloWorldImportSelector.class) // 有弹性
public @interface EnableHelloWorld {

}
