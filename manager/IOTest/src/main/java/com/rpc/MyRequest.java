package com.rpc;

import java.io.Serializable;

public class MyRequest<T> implements Serializable {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public MyRequest(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MyRequest{" +
                "data=" + data +
                '}';
    }
}
