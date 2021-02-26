package com.my;



import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main1(String[] args) {
        ServiceLoader<Developer> serviceLoader = ServiceLoader.load(Developer.class);
        //serviceLoader.forEach(Developer::sayHi);

        Iterator<Developer> driversIterator = serviceLoader.iterator();

        /* Load these drivers, so that they can be instantiated.
         * It may be the case that the driver class may not be there
         * i.e. there may be a packaged driver with the service class
         * as implementation of java.sql.Driver but the actual class
         * may be missing. In that case a java.util.ServiceConfigurationError
         * will be thrown at runtime by the VM trying to locate
         * and load the service.
         *
         * Adding a try catch block to catch those runtime errors
         * if driver not available in classpath but it's
         * packaged as service and that service is there in classpath.
         */
        try {
            while (driversIterator.hasNext()) {
                driversIterator.next();
                Class<? extends Iterator> aClass = driversIterator.getClass();
            }
        } catch (Throwable t) {
            // Do nothing
        }

    }

    public static void main2(String[] args) {

        Teacher p = new Teacher();
        p.setId(1);
        p.setAge(2L);

        Person pp = new Person();
        pp.setTeacher(p);



//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        new Thread(() -> {
//
//            Object o = new Object();
//            synchronized (o){
//                System.out.println(ClassLayout.parseInstance(o).toPrintable());
//            }
//        }).start();

//        new Thread(() -> {
//
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
////            System.out.println(ClassLayout.parseInstance(o).toPrintable());
//            synchronized (o){
//                System.out.println(ClassLayout.parseInstance(o).toPrintable());
//            }
//        }).start();


    }

    private static int x = 0;
    private static int y = 0;

    public static void main(String[] args) {

        new Thread(() -> {
            while (true) {
                synchronized (Test.class) {
                }
                if (x == 1 & y == 2) {
                    System.out.println("thread1 end");
                    break;
                }
            }

        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            x = 1;
            y = 2;
        }).start();

        System.out.println("main is end");


    }


}

