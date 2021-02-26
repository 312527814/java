package com.rpc;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import javax.xml.ws.Response;
import java.util.List;

public class ClientDecode extends ByteToMessageDecoder {

    //父类里一定有channelread{  前老的拼buf  decode（）；剩余留存 ;对out遍历 } -> bytebuf
    //因为你偷懒，自己能不能实现！
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {

        while (buf.readableBytes() >= 85) {
            byte[] bytes = new byte[85];
            buf.getBytes(buf.readerIndex(), bytes);  //从哪里读取，读多少，但是readindex不变
            MyHeader header = SerDerUtil.deser(MyHeader.class, bytes);
            //DECODE在2个方向都使用
            //通信的协议
            if (buf.readableBytes() - 85 >= header.getDataLength()) {
                //处理指针
                buf.readBytes(85);  //移动指针到body开始的位置
                byte[] data = new byte[(int) header.getDataLength()];
                buf.readBytes(data);

                MyResponse response = SerDerUtil.deser(MyResponse.class, data);
                Packmsg packmsg = new Packmsg(header, response);
                out.add(packmsg);
            } else {
                break;
            }


        }

    }
}

