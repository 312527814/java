package com.rpc;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.util.concurrent.CompletableFuture;

public class ProxyFactory {


    public <T> T creatProxy(Class<?> interfaceInfo) {
        Object o = new Object();
        //Class<?> aClass = o.getClass();
        ClassLoader classLoader = interfaceInfo.getClassLoader();
        Class<?>[] interfaces = {interfaceInfo};
        return (T) Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (Object.class.equals(method.getDeclaringClass())) {
                    return method.invoke(this, args);
                }
                MyContext myContext = new MyContext(interfaceInfo.getName(), method.getName(), args, method.getParameterTypes());
                MyRequest<MyContext> request = new MyRequest<>(myContext);
                byte[] requestBytes = SerDerUtil.ser(request);
//                ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(requestBytes.length);
//                byteBuf.writeBytes(requestBytes);

//                Object res = RPCUtils.send(new InetSocketAddress("localhost", 9090), byteBuf);
//                return res;
                ClientFactroy factroy = ClientFactroy.build();
//                MyClient client = factroy.getClient(new InetSocketAddress("192.168.77.131", 9090));
                MyClient client = factroy.getClient(new InetSocketAddress("localhost", 9090));

//                byte[] data = new byte[byteBuf.writerIndex()];
//                byteBuf.readBytes(data);
                Object res = client.send(requestBytes);
                return res;

            }
        });
    }


}
