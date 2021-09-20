package com.my;

import com.my.bean.Person;
import com.my.config.MyImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-09-20 18:27
 */
@Import(MyImportBeanDefinitionRegistrar.class)
public class _2ImportBeanDefinitionRegistrarApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("ImportBeanDefinitionRegistrarApplicationContext.xml");
        Person bean = ac.getBean(Person.class);

        System.out.println("person " + bean.getName());
    }
}
