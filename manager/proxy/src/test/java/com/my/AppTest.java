package com.my;

import static org.junit.Assert.assertTrue;

import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Unit test for simple App.
 */
public class AppTest {

    public static void main(String[] args)  throws  Exception{


            File filpath = new File("");
            File absoluteFile = filpath.getAbsoluteFile();
            File xFile=new File("H:\\javalianxi\\manager\\jartest\\target\\jartest-1.0-SNAPSHOT.jar");
            URL  xUrl= xFile.toURL() ;

//        String path = "file:" + "E:\\CompanyProject\\pmp-api\\target\\classes\\com\\ikang\\pmp";
//        URL newurl = new URL(path);

            URLClassLoader classLoader = new URLClassLoader(new URL[]{xUrl});

            Class<?> mainClass = classLoader.loadClass("com.my.JarApp");


            //Class<?> mainClass = Thread.currentThread().getContextClassLoader().loadClass(this.mainClassName);
            Method mainMethod = mainClass.getDeclaredMethod("main", String[].class);
            mainMethod.invoke((Object) null, new String[2]);


        }

}
