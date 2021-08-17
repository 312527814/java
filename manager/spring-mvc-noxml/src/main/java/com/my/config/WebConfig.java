package com.my.config;

import com.my.interceptor.Myinterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Myinterceptor())
                .addPathPatterns("/**")                     //所有路径都被拦截
                .excludePathPatterns(                         //添加不拦截路径
                        "/**/*.html",            //html静态资源
                        "/**/*.js",              //js静态资源
                        "/**/*.css",             //css静态资源
                        "/**/*.woff",
                        "/**/*.ttf",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/api-docs",
                        "/**swagger**/**"
                );

    }
}
