package com.my.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.core.type.AnnotationMetadata;
import com.my.annotation.MyEnableAutoConfiguration;

import java.lang.reflect.Array;
import java.util.List;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-09-20 18:44
 */
public class MyImportSelectorAuto implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> configurations = SpringFactoriesLoader
                .loadFactoryNames(MyEnableAutoConfiguration.class, MyEnableAutoConfiguration.class.getClassLoader());
        return configurations.toArray(new String[]{});
    }
}
