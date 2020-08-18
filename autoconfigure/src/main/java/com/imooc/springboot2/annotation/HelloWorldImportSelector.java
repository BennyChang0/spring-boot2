package com.imooc.springboot2.annotation;

import com.imooc.springboot2.configuration.HelloWorldConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class HelloWorldImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        // 根据条件选择
        return new String[]{HelloWorldConfiguration.class.getName()};
    }
}
