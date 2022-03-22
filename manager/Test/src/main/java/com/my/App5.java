package com.my;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * Hello world!
 */
public class App5 {


    public static void main(String[] args) throws IOException {

        byte[] bytes = "dwdwdefe".getBytes();
        ByteInputStream byteInputStream = new ByteInputStream();
        byteInputStream.setBuf(bytes);
        BufferedReader reader = new BufferedReader(new InputStreamReader(byteInputStream));

        BufferedReader reader2 = new BufferedReader(new InputStreamReader(byteInputStream));

        String s3 = IOUtils.toString(reader2);
        String s2 = IOUtils.toString(reader);

        String s = reader.readLine();
        String s1 = reader.readLine();
        throw  new Error("dd");
    }

}
