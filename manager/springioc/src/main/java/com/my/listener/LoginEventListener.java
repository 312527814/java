package com.my.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

public class LoginEventListener implements ApplicationListener<LoginEvent> {


    @Override
    public void onApplicationEvent(LoginEvent loginEvent) {
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print(loginEvent.getSource());
    }
}
