package com.my;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-09-18 11:34
 */
public class MetaspaceFullGC {
    public static void main(String[] args) throws InterruptedException {


        List<Class> classes = new LinkedList<>();

//        for (int j = 0; j < 100000000; j++) {
//            Thread.sleep(1);
//            Class<?> classe = Metaspace.createClasses();
//            classes.add(classe);
//        }

        Metaspace.createClasses3();
    }

    static class Metaspace extends ClassLoader {

        public static List<Class<?>> createClasses2() throws InterruptedException {
            String classname = "class" + UUID.randomUUID().toString().replace("-", "");
            // 类持有
            List<Class<?>> classes = new ArrayList<Class<?>>();
            // 循环1000w次生成1000w个不同的类。
            for (int i = 0; i < 10000000; ++i) {
                Thread.sleep(10);
                ClassWriter cw = new ClassWriter(0);
                // 定义一个类名称为Class{i}，它的访问域为public，父类为java.lang.Object，不实现任何接口
                cw.visit(Opcodes.V1_1, Opcodes.ACC_PUBLIC, classname, null,
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
                MetaspaceFullGC.Metaspace test = new MetaspaceFullGC.Metaspace();
                byte[] code = cw.toByteArray();
                // 定义类
                Class<?> exampleClass = test.defineClass(classname, code, 0, code.length);
                classes.add(exampleClass);

//                System.out.println(exampleClass.getName());
            }
            return classes;
        }

        public static Class<?> createClasses() throws InterruptedException {
            String classname = "class" + UUID.randomUUID().toString().replace("-", "");
            ClassWriter cw = new ClassWriter(0);
            // 定义一个类名称为Class{i}，它的访问域为public，父类为java.lang.Object，不实现任何接口
            cw.visit(Opcodes.V1_1, Opcodes.ACC_PUBLIC, classname, null,
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
            MetaspaceFullGC.Metaspace test = new MetaspaceFullGC.Metaspace();
            byte[] code = cw.toByteArray();
            // 定义类
            Class<?> exampleClass = test.defineClass(classname, code, 0, code.length);
            return exampleClass;
//                System.out.println(exampleClass.getName());
        }

        public static void createClasses3() {
            MetaspaceFullGC.Metaspace test = new MetaspaceFullGC.Metaspace();
            for (int i = 0; i < 100000000; i++) {
                String classname = "class" + UUID.randomUUID().toString().replace("-", "");
                byte[] code = getCode(classname);
                Class<?> exampleClass = test.defineClass(classname, code, 0, code.length);
            }


        }

        public static byte[] getCode(String classname) {


            ClassWriter cw = new ClassWriter(0);
            // 定义一个类名称为Class{i}，它的访问域为public，父类为java.lang.Object，不实现任何接口
            cw.visit(Opcodes.V1_1, Opcodes.ACC_PUBLIC, classname, null,
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
            MetaspaceFullGC.Metaspace test = new MetaspaceFullGC.Metaspace();
            byte[] code = cw.toByteArray();

            return code;
        }
    }
}
