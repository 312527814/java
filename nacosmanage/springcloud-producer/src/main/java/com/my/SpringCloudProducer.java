package com.my;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudProducer {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudProducer.class, args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);//单位为ms
        factory.setConnectTimeout(5000);//单位为ms
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.getInterceptors().add(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                return execution.execute(request, body);
            }
        });
        return restTemplate;
    }

    @Bean
    public RestTemplate restTemplate2() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);//单位为ms
        factory.setConnectTimeout(5000);//单位为ms
        return new RestTemplate(factory);
    }

}
