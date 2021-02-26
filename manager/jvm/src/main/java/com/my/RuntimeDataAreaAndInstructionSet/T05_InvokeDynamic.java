package com.my.RuntimeDataAreaAndInstructionSet;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.ArrayList;
import java.util.List;

public class T05_InvokeDynamic {
    static String base = "string";

    public static List<Class> list = new ArrayList<>();

    public static List<Class> list2 = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        String arch = System.getProperty("sun.arch.data.model");
        System.out.println(arch + "-bit");
        I i = C::n;
        I i2 = C::n;
        I i3 = C::n;
        I i4 = () -> {
            C.n();
        };

        System.out.println(i.getClass());
        System.out.println(i2.getClass());
        System.out.println(i3.getClass());

//        new Thread(() -> {
//            while (true) {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                System.out.print("\r\nlist=>" + list.size());
//
//                System.out.print("\r\nlist2=>" + list2.size());
//            }
//        }).start();


//-XX:MaxMetaspaceSize=30M -XX:+PrintGCDetails
//        for (; ; ) {
//            Thread.sleep(1);
//            I j = () -> {
//                C.n();
//            };
//            Class<? extends I> aClass = j.getClass();
//            list.add(aClass);
//            if (list.contains(aClass)) {
//                list2.add(aClass);
//            }
//        } //MethodArea <1.8 Perm Space (FGC不回收)

        for (int j = 0; j <10000000 ; j++) {
            Thread.sleep(1);
            Metaspace.createClasses(j);
        }
    }

    @FunctionalInterface
    public interface I {
        void m();
    }

    public static class C {
        static void n() {
            System.out.println("hello");
        }
    }

    static class Metaspace extends ClassLoader {

        public static Class createClasses(int i) throws InterruptedException {
            // 类持有
            // 循环1000w次生成1000w个不同的类。
            ClassWriter cw = new ClassWriter(0);
            // 定义一个类名称为Class{i}，它的访问域为public，父类为java.lang.Object，不实现任何接口
            cw.visit(Opcodes.V1_1, Opcodes.ACC_PUBLIC, "Class" + i, null,
                    "java/lang/Object", null);
            // 定义构造函数<init>方法
            MethodVisitor mw = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>",
                    "()V", null, null);
            // 第一个指令为加载this
            mw.visitVarInsn(Opcodes.ALOAD, 0);
            // 第二个指令为调用父类Object的构造函数
            mw.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object",
                    "<init>", "()V");
            // 第三条指令为return
            mw.visitInsn(Opcodes.RETURN);
            mw.visitMaxs(1, 1);
            mw.visitEnd();
            Metaspace test = new Metaspace();
            byte[] code = cw.toByteArray();
            // 定义类
            Class<?> exampleClass = test.defineClass("Class" + i, code, 0, code.length);
            return exampleClass;
        }
    }

}

