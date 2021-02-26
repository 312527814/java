package com.testreactor2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

public class SelectorThreadGroup {  //天生都是boss

    String name;
    SelectorThread[] sts;
    ServerSocketChannel server = null;
    AtomicInteger xid = new AtomicInteger(0);

    SelectorThreadGroup work = this;


    public void setWork(SelectorThreadGroup work) {
        this.work = work;
    }

    SelectorThreadGroup(int num, String name) {
        this.name = name;
        //num  线程数
        sts = new SelectorThread[num];
        for (int i = 0; i < num; i++) {
            sts[i] = new SelectorThread(this);
            new Thread(sts[i], "th" + name + i).start();
        }

    }


    public void bind(int port) {

        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));

            //注册到那个selector上呢？
            registerSelector(server);
            //nextSelectorV3(server);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void registerSelector(Channel c) {
        if (c instanceof ServerSocketChannel) {
            SelectorThread st = selectorBoss();  //在 main线程种，取到堆里的selectorThread对象
            //1,通过队列传递数据 消息
            st.lbq.add(c);
            //2,通过打断阻塞，让对应的线程去自己在打断后完成注册selector
            st.selector.wakeup();
        } else if (c instanceof SocketChannel) {

            SelectorThread st = selectorWork();  //在 main线程种，取到堆里的selectorThread对象

            //1,通过队列传递数据 消息
            st.lbq.add(c);
            //2,通过打断阻塞，让对应的线程去自己在打断后完成注册selector
            st.selector.wakeup();
        }
    }


    //无论 serversocket  socket  都复用这个方法
    private SelectorThread selectorBoss() {
        int index = xid.incrementAndGet() % sts.length;  //轮询就会很尴尬，倾斜
        return sts[index];
    }

    private SelectorThread selectorWork() {
        int index = xid.incrementAndGet() % this.work.sts.length;  //轮询就会很尴尬，倾斜
        return this.work.sts[index];
    }


}
