package com.my.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-09-20 18:36
 */
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{
                MyConfiguration.class.getName()
        };


    }
}
