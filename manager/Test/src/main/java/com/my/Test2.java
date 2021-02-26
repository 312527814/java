package com.my;

import java.util.concurrent.TimeUnit;

public class Test2 {
    volatile T o = new T();
}

class T {
    int m = 2;
}
