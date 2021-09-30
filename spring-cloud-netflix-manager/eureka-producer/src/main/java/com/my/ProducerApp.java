package com.my;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 */
@SpringBootApplication
public class ProducerApp {
    public static void main(String[] args) {
        SpringApplication.run(ProducerApp.class, args);
    }

//    @Bean
//    public ServletRegistrationBean servletRegistrationBean() {
//        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(
//                new MyServlet(), new String[0]);
//        servletRegistrationBean.setLoadOnStartup(-10000);
//        return servletRegistrationBean;
//    }
}
