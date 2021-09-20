package com.my;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );

        ByteBuf buffer = Unpooled.buffer(10);
        buffer.writeByte(95);
        buffer.writeByte(95);
        buffer.writeByte(95);
        buffer.writeByte(95);

        while (true){
            byte b = buffer.readByte();
            System.out.println(b);
            if(buffer.writerIndex()==buffer.readerIndex()){
                buffer.resetReaderIndex();
            }
        }



//        byte b = buffer.readByte();
//
//
//        buffer.resetWriterIndex();
//
//        byte b1 = buffer.readByte();
    }
}
