package com.my;


public class Person {
//    @Override
//    protected void finalize() throws Throwable {
//        System.out.print("\r\nfinalize:" + ":" + this.getName());
//    }

    public static Teacher teacher5 = new Teacher();
    private String name;
    private int age;

    private Teacher teacher;

    private Teacher teacher2;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getTeacher2() {
        return teacher2;
    }

    public void setTeacher2(Teacher teacher2) {
        this.teacher2 = teacher2;
    }

    public synchronized String getName() {
        System.out.println("getname start \r\n");
//        System.out.println(ClassLayout.parseInstance(this).toPrintable());
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public synchronized int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
