package com.my;


import com.alibaba.fastjson.JSON;

import java.io.*;
import java.nio.ByteBuffer;

public class Tests {


    public static void main(String[] args) throws Exception {

        Person person = new Person();
        person.setName("李四");
        writeObj2(person);
    }



    public static void writeObj2(Object o) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("H:/javalianxi/manager/Test/user.txt"));
            String string = JSON.toJSONString(o);
            objectOutputStream.writeObject(string);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeObj(Object o) {
        try {
            String string = JSON.toJSONString(o);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("H:/javalianxi/manager/Test/user.txt"));

            objectOutputStream.writeObject(string);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object readObj() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("H:/javalianxi/manager/Test/user.txt"));
            try {
                Object object = objectInputStream.readObject();
                Person parse = JSON.parseObject((String) object, Person.class);
                return parse;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}




