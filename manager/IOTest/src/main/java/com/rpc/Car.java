package com.rpc;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;

public class Car implements ICar {
    @Override
    public void get(String msg) {

    }

    @Override
    public Object getr(String msg) {

        return "";
    }
}
