package com.my.jdk;

public class Boss implements IBoss  {
    private int age=10;
    public void Say(String message) {
        System.out.println("hello " + message+age);
    }

}