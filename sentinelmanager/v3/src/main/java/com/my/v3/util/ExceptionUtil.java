package com.my.v3.util;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class ExceptionUtil {
    public static String handleException(BlockException e) {
        return "bock.." + e;

    }
}
