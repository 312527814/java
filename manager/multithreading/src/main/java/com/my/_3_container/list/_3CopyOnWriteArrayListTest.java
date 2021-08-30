package com.my._3_container.list;

import java.util.concurrent.CopyOnWriteArrayList;

public class _3CopyOnWriteArrayListTest {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.add("a");
        copyOnWriteArrayList.get(1);
    }
}
