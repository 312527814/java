package com.my;

public class ServerGroupApp {
    public static void main(String[] args) {
        ServerGroup main = new ServerGroup("main");
        ServerGroup work = new ServerGroup("work1");
        ServerGroup work2 = new ServerGroup("work2");
        work.setWorkGroup(work2);
        main.setWorkGroup(work);
        main.bind(8089);
        main.start();
    }
}
