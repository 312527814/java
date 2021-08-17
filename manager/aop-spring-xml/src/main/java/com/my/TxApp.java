package com.my;

import com.my.service.MyService;
import com.my.tx.service.IMyTransService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class TxApp
{
    public static void main( String[] args )
    {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("txApplicationContext.xml");
        IMyTransService bean = ac.getBean(IMyTransService.class);
        int run = bean.insert();
    }
}
