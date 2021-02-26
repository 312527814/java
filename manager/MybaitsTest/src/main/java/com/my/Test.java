package com.my;

import java.util.Iterator;
import java.util.ServiceLoader;

public class Test {
    public static void main(String[] args) {
        Pair<String> s=new Pair<>();
        s.setValue("sdads");
        String value = s.getValue();

        MyPair my=new MyPair();

        ServiceLoader<Developer> serviceLoader = ServiceLoader.load(Developer.class);

        Iterator<Developer> driversIterator = serviceLoader.iterator();
        try{
            while(driversIterator.hasNext()) {
                driversIterator.next();
                Class<? extends Iterator> aClass = driversIterator.getClass();
            }
        } catch(Throwable t) {
            // Do nothing
        }

    }
}
