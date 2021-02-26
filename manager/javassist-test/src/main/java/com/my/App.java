package com.my;

import javassist.*;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.Method;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {

//        Proxy<Person> proxy = new Proxy(Person.class);
//        Person proxyOject = proxy.getProxyOject();
//        proxyOject.say();
//        System.out.println("Hello World!");
//
        t4();
        t2();


//        t3(PersonImpl.class, "say");
        t3("com.my.PersonImpl", "say");
        ClassLoader classLoader = App.class.getClassLoader();

        Person person = new PersonImpl();
        person.say();

    }


    public static void t2() throws Exception {
        // 类库池, jvm中所加载的class
        ClassPool pool = ClassPool.getDefault();

        CtClass orNull = pool.getOrNull("com.itheima.Student");
        // 获取指定的Student类
        CtClass ctClass = pool.makeClass("com.itheima.Student");

        CtClass ctClass2 = pool.get("com.itheima.Student");
        // 创建calc方法, 带两个参数，参数的类型都为int类型
        CtMethod ctMethod = new CtMethod(CtClass.intType, "calc",
                new CtClass[]{CtClass.intType, CtClass.intType}, ctClass);
        // 设置方法的访问修饰
        ctMethod.setModifiers(Modifier.PUBLIC);
        // 设置方法体代码
        ctMethod.setBody("return $1 + $2;");

        // 添加新建的方法到原有的类中
        ctClass.addMethod(ctMethod);
        // 加载修改后的类
        Class aClass = ctClass.toClass();
        Object o = aClass.newInstance();
        // 创建对象
        //Student stu = new Student();
        // 获取calc方法
        Method dMethod = aClass.getDeclaredMethod("calc", new Class[]
                {int.class, int.class});
        // 反射调用 方法
        Object result = dMethod.invoke(o, 10, 20);
        // 打印结果
        System.out.println(String.format("调用calc方法，传入参数：%d,%d", 10, 20));
        System.out.println("返回结果：" + (int) result);
    }

    public static void t3(Class aclass, String methodName) {


        // 类库池, jvm中所加载的class
        ClassPool pool = ClassPool.getDefault();
        // 获取指定的Student类
        CtClass ctClass = null;
        try {
            ctClass = pool.get(aclass.getName());
            CtMethod declaredMethod = ctClass.getDeclaredMethod(methodName);
            declaredMethod.insertAfter("System.out.println(\"hand up my homework from proxy Object\");");
            aclass = null;
            System.gc();
            Thread.sleep(1000);
            ctClass.toClass();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void t3(String className, String methodName) {
        // 类库池, jvm中所加载的class
        ClassPool pool = ClassPool.getDefault();
        // 获取指定的Student类
        CtClass ctClass = null;
        try {
            ctClass = pool.get(className);
            CtMethod declaredMethod = ctClass.getDeclaredMethod(methodName);
            declaredMethod.insertAfter("System.out.println(\"hand up my homework from proxy Object\");");
            ctClass.toClass();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void t4() throws Exception {
        // 类库池, jvm中所加载的class
        ClassPool pool = ClassPool.getDefault();
        Class personClass = PersonImpl.class;
        CtClass ctClass = pool.getOrNull("com.my.PersonImpl");
        // 创建calc方法, 带两个参数，参数的类型都为int类型
        CtMethod ctMethod = new CtMethod(CtClass.intType, "calc",
                new CtClass[]{CtClass.intType, CtClass.intType}, ctClass);
        // 设置方法的访问修饰
        ctMethod.setModifiers(Modifier.PUBLIC);
        // 设置方法体代码
        ctMethod.setBody("return $1 + $2;");

        // 添加新建的方法到原有的类中
        ctClass.addMethod(ctMethod);

        // 加载修改后的类
        Class aClass = ctClass.toClass();
        Object o = aClass.newInstance();
        // 创建对象
        //Student stu = new Student();
        // 获取calc方法
        Method dMethod = aClass.getDeclaredMethod("calc", new Class[]
                {int.class, int.class});
        // 反射调用 方法
        Object result = dMethod.invoke(o, 10, 20);
        // 打印结果
        System.out.println(String.format("调用calc方法，传入参数：%d,%d", 10, 20));
        System.out.println("返回结果：" + (int) result);
    }
}
