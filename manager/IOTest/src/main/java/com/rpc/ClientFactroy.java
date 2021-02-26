package com.rpc;

import com.netty.MyReadHanlder;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

class ClientFactroy {
    private ConcurrentHashMap<InetSocketAddress, ClientPool> outboxs = new ConcurrentHashMap<>();
    private int poolSize = 1;

    private ClientFactroy() {
    }

    private static ClientFactroy factroy = new ClientFactroy();
    ;

    public static ClientFactroy build() {
        return factroy;
    }

    public synchronized MyClient getClient(InetSocketAddress address) {
        ClientPool clientPool = outboxs.get(address);
        if (clientPool == null) {
            clientPool = new ClientPool(poolSize);
            outboxs.put(address, clientPool);
        }
        int index = new Random().nextInt(poolSize);
        MyClient client = clientPool.getChannels()[index];
        if (client == null) {
            client = new MyClient(address);
            clientPool.getChannels()[index] = client;
        }
        return client;
    }
}
