package com.my;

import com.my.service.MyService;
import com.my.service.MyServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class SpringIocJdbcApp {
    public static void main(String[] args) throws Exception {

//        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "e:\\class");
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-jdbc.xml");
        for (String s : ac.getBeanDefinitionNames()) {
            System.out.println("\r\n" + s);
        }
        MyService myService = ac.getBean(MyService.class);
        myService.insert();


//


    }


}
