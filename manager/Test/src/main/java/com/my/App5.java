package com.my;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.commons.io.IOUtils;
import org.mortbay.util.MultiMap;
import org.mortbay.util.UrlEncoded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * Hello world!
 */
public class App5 {


    public static void main(String[] args) throws IOException {

        String url = "http://localhost:8080/test?a=12/f3w==&b=456";
//参数解析
        MultiMap multiMap = new MultiMap();
        //UrlEncoded.decodeTo(url.substring(url.indexOf("?") + 1), multiMap, "UTF-8");
        //System.out.println(multiMap.toString());

        parseParameters(multiMap,url.substring(url.indexOf("?") + 1),"UTF-8");

        System.out.println(multiMap.toString());
    }

    public static void parseParameters(Map map, String data, String encoding)
            throws UnsupportedEncodingException
    {
        if ((data == null) || (data.length() <= 0))
        {
            return;
        }

        byte[] bytes = null;
        try {
            if (encoding == null) {
                bytes = data.getBytes();
            } else {
                bytes = data.getBytes(encoding);
            }
        }
        catch (UnsupportedEncodingException uee)
        {
        }
        parseParameters(map, bytes, encoding);
    }

    public static void parseParameters(Map map, byte[] data, String encoding) throws UnsupportedEncodingException
    {
        if ((data != null) && (data.length > 0)) {
            int ix = 0;
            int ox = 0;
            String key = null;
            String value = null;
            while (ix < data.length) {
                byte c = data[(ix++)];
                switch ((char)c)
                {
                    case '&':
                        value = new String(data, 0, ox, encoding);
                        if (key != null) {
                            putMapEntry(map, key, value);
                            key = null;
                        }
                        ox = 0;
                        break;
                    case '=':
                        if (key == null) {
                            key = new String(data, 0, ox, encoding);
                            ox = 0;
                        } else {
                            data[(ox++)] = c;
                        }
                        break;
                    case '+':
                        data[(ox++)] = 32;
                        break;
                    case '%':
                        data[(ox++)] = (byte)((convertHexDigit(data[(ix++)]) << 4) + convertHexDigit(data[(ix++)]));

                        break;
                    default:
                        data[(ox++)] = c;
                }
            }

            if (key != null) {
                value = new String(data, 0, ox, encoding);
                putMapEntry(map, key, value);
            }
        }
    }
    private static void putMapEntry(Map map, String name, String value)
    {
        String[] newValues = null;
        String[] oldValues = (String[])(String[])map.get(name);
        if (oldValues == null) {
            newValues = new String[1];
            newValues[0] = value;
        } else {
            newValues = new String[oldValues.length + 1];
            System.arraycopy(oldValues, 0, newValues, 0, oldValues.length);
            newValues[oldValues.length] = value;
        }
        map.put(name, newValues);
    }
    private static byte convertHexDigit(byte b)
    {
        if ((b >= 48) && (b <= 57)) {
            return (byte)(b - 48);
        }
        if ((b >= 97) && (b <= 102)) {
            return (byte)(b - 97 + 10);
        }
        if ((b >= 65) && (b <= 70)) {
            return (byte)(b - 65 + 10);
        }
        return 0;
    }

}
