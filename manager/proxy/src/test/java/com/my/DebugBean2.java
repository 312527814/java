package com.my;

public class DebugBean2 {
    private int id;
    private String name;

    @Override
    public String toString() {
        System.out.println("调用2toString()");
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
