package com.my;

import com.my.servlet.MyServlet3;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ServletComponentScan //在springBoot 启动时会扫描@WebServlet，并将该类实例化
public class ServletApp {
    public static void main(String[] args) {
        SpringApplication.run(ServletApp.class, args);
    }

    @Bean
    public ServletRegistrationBean zuulServlet() {
//        ServletRegistrationBean<MyServlet3> servlet = new ServletRegistrationBean<>(
//                new MyServlet3(), "/servlet3");
//        // The whole point of exposing this servlet is to provide a route that doesn't
//        // buffer requests.
//        servlet.addInitParameter("buffer-requests", "false");



        ServletRegistrationBean bean = new ServletRegistrationBean(new MyServlet3());     //放入自己的Servlet对象实例
        bean.addUrlMappings("/servlet3");  //访问路径值
        return bean;


//        return servlet;
    }
}
