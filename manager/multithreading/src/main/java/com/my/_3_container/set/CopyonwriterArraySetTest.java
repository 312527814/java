package com.my._3_container.set;

import java.util.concurrent.CopyOnWriteArraySet;

public class CopyonwriterArraySetTest {
    public static void main(String[] args) {
        CopyOnWriteArraySet<String> stringCopyOnWriteArraySet=new CopyOnWriteArraySet<>();
        boolean a = stringCopyOnWriteArraySet.add("a");
        boolean a1 = stringCopyOnWriteArraySet.add("a");

    }
}
