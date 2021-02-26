package com.rpc;

import com.my.Client;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ClientPool {
    public MyClient[] getChannels() {
        return clients;
    }
    private MyClient[] clients = null;

    public ClientPool(int size) {
        this.clients = new MyClient[size];
    }
}
