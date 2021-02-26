package com.you;

import org.springframework.context.annotation.Bean;

public class MyConfiguration {
    @Bean
    public YouTest getYouTest() {
        return new YouTest();
    }
}
