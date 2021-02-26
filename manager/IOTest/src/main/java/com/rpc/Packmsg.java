package com.rpc;

public class Packmsg {
    private MyHeader header;
    private Object body;

    public MyHeader getHeader() {
        return header;
    }

    public void setHeader(MyHeader header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public Packmsg(MyHeader header, Object body) {
        this.header = header;
        this.body = body;
    }
}
