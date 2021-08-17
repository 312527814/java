package com.my;

import com.my.config.ProvideConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.CountDownLatch;

public class ProviderApp {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProvideConfig.class);
        context.start();
       new CountDownLatch(1).await();
    }
}
