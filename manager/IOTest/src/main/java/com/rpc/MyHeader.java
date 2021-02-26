package com.rpc;

import java.io.Serializable;
import java.util.UUID;

public class MyHeader implements Serializable {
    private int flag;
    private int dataLength;
    private long requestId;

    public MyHeader( int dataLength) {
        this.flag = 0x141414;
        this.dataLength = dataLength;
        this.requestId =Math.abs(UUID.randomUUID().getLeastSignificantBits());
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getDataLength() {
        return dataLength;
    }

    public void setDataLength(int dataLength) {
        this.dataLength = dataLength;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    @Override
    public String toString() {
        return "MyHeader{" +
                "flag=" + flag +
                ", dataLength=" + dataLength +
                ", requestId=" + requestId +
                '}';
    }
}
