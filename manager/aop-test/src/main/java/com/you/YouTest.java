package com.you;

import org.springframework.context.annotation.Bean;
@EnableYou
public class YouTest {
    @Bean
    public YouTest ss() {
        return new YouTest();
    }
}
