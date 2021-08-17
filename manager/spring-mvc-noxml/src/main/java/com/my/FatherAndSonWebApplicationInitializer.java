package com.my;

import com.my.config.FatherConfig;
import com.my.config.SonConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//父子容器
public class FatherAndSonWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{FatherConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {

        return new Class[]{SonConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/father_and_son/*"};
    }

}
