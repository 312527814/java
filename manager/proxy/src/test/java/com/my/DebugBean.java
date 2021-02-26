package com.my;

public class DebugBean {
    private int id;
    private String name;

    @Override
    public String toString() {
        System.out.println("调用toString()");
        return super.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
