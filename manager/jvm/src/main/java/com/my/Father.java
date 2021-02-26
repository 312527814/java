package com.my;

class Father{

    public void fMe(){
        System.out.println("fMe");
        fMe1();//invovespecial调用
        System.out.println(this);
        this.fMe1();//invovespecial调用
    }

    public void fMe1(){
        System.out.println("fMe1");
    }
}