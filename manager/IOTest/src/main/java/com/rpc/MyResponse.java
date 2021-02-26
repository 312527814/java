package com.rpc;

import java.io.Serializable;

public class MyResponse<T> implements Serializable {
    private  T msg;

    public MyResponse(T msg) {
        this.msg = msg;
    }

    public T getMsg() {
        return msg;
    }

    public void setMsg(T msg) {
        this.msg = msg;
    }
}
