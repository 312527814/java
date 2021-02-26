package com.my;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.nio.ByteBuffer;

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
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        //byteBuffer.put("ddd".getBytes());
        byteBuffer.mark();
        byte[] bytes = "rrrrrrrrr".getBytes();
        byteBuffer.put(bytes);
        byteBuffer.reset();
        int aLong = byteBuffer.getInt();
        assertTrue( true );
    }
}
