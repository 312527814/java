package com.my.log;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-10-09 18:57
 */
public class LoggerInfo {
    private String message;
    private String level;
    private String application;
    private String host;
    private int port;
    private String traceId;
    private String spanceId;
    private String TheadName;
    private String dateTime;
    private String className;
    private int line;


    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getSpanceId() {
        return spanceId;
    }

    public void setSpanceId(String spanceId) {
        this.spanceId = spanceId;
    }

    public String getTheadName() {
        return TheadName;
    }

    public void setTheadName(String theadName) {
        TheadName = theadName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
