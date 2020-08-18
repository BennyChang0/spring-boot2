package com.imooc.diveinspringboot.externalized.configuration.listener;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

public class ExtendPropertySourcesEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        System.err.println("ExtendPropertySourcesEventListener event");
        ConfigurableEnvironment environment = event.getEnvironment();

        MutablePropertySources propertySources = environment.getPropertySources();
        Map<String, Object> source = new HashMap<>();
        // 1. from-environmentPrepared: 0
        // 2. from-ApplicationEnvironmentPreparedEvent : 9
        // application.properties : 1
        // META-INF/default.properties : 7
        source.put("user.id", 9);
        MapPropertySource propertySource = new MapPropertySource("from-ApplicationEnvironmentPreparedEvent", source);
        propertySources.addFirst(propertySource);
    }
}
