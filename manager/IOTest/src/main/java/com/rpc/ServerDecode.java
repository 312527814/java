package com.rpc;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class ServerDecode extends ByteToMessageDecoder {

    //父类里一定有channelread{  前老的拼buf  decode（）；剩余留存 ;对out遍历 } -> bytebuf
    //因为你偷懒，自己能不能实现！
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
        System.out.println("while start ....................." + this.hashCode() + ";" + buf.hashCode() + ":readerIndex:" + buf.readerIndex() + ";writer:" + buf.writerIndex() + "capacity;" + buf.capacity() + ";maxCapacity" + buf.maxCapacity());
        while (buf.readableBytes() >= 85) {
            byte[] bytes = new byte[85];
            buf.getBytes(buf.readerIndex(), bytes);  //从哪里读取，读多少，但是readindex不变
            MyHeader header = SerDerUtil.deser(MyHeader.class, bytes);
            //DECODE在2个方向都使用
            //通信的协议
            if (buf.readableBytes() - 85 >= header.getDataLength()) {
                //处理指针
                try {
//                    byte[] bytes2 = new byte[85];
//                    buf.readBytes(bytes2);
                    System.out.println(this.hashCode() + ";" + buf.hashCode() + ":readerIndex:" + buf.readerIndex() + ";writer:" + buf.writerIndex() + "capacity;" + buf.capacity() + ";maxCapacity" + buf.maxCapacity());
                    buf.readBytes(85);  //移动指针到body开始的位置

                } catch (Exception ex) {

                    int a = 0;
                    ex.printStackTrace();
                }

                byte[] data = new byte[(int) header.getDataLength()];
                buf.readBytes(data);

                MyRequest<MyContext> requst = SerDerUtil.deser(MyRequest.class, data);
                Packmsg packmsg = new Packmsg(header, requst);
                out.add(packmsg);


            } else {
                break;
            }


        }
        byte[] bytes = new byte[buf.writerIndex() - buf.readerIndex()];
        buf.readBytes(bytes);

//        buf.resetReaderIndex();
//        buf.resetWriterIndex();
//        buf.writeBytes(bytes);
        buf.release();

        ByteBuf buffer = PooledByteBufAllocator.DEFAULT.buffer(bytes.length + 1);
        buffer.writeBytes(bytes);
        buf = buffer;
        System.out.println("while end ....................." + this.hashCode() + ";" + buf.hashCode() + ":readerIndex:" + buf.readerIndex() + ";writer:" + buf.writerIndex() + "capacity;" + buf.capacity() + ";maxCapacity" + buf.maxCapacity());

    }
}

