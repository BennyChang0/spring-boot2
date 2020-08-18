package com.imooc.springboot2;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApplicationEventBootstrap {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.addApplicationListener(event -> {
            System.out.println("监听到事件：" + event);
        });

        context.refresh();

        context.publishEvent("HelloWorld");
        context.publishEvent("2019");
        context.publishEvent(new ApplicationEvent("xiamage") {
        });

        context.close();
    }
}
