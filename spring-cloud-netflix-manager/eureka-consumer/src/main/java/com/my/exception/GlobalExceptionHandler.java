package com.my.exception;


import com.my.log.LoggerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @Value("${spring.application.name}")
    private String application;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String handleException(Exception ex, HttpServletRequest request) {
        String spanId = MDC.get("spanId");
        String traceId = MDC.get("traceId");
        String obj2String = ex.toString();
        StackTraceElement[] stackTrace = ex.getStackTrace();
        String stackTraceStr = "";
        if (stackTrace != null) {
            StringBuffer sb = new StringBuffer(stackTrace.length);
            for (int i = 0; i < stackTrace.length; i++) {
                StackTraceElement element = stackTrace[i];
                sb.append("at " + element.getClassName() + "." + element.getMethodName() + "(" + element.getFileName() + ":" + element.getLineNumber() + ")\n");
            }
            stackTraceStr = sb.toString();
        }
        log.error(obj2String);
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSSS", Locale.CHINA).format(new Date());
        LoggerInfo loggerInfo = new LoggerInfo();
        loggerInfo.setApplication(application);
        loggerInfo.setHost("127.0.0.1");
        loggerInfo.setLevel("error");
        loggerInfo.setPort(8080);
        loggerInfo.setMessage(obj2String + "\nStackTrace\n" + stackTraceStr);
        loggerInfo.setLine(0);
        loggerInfo.setTheadName(Thread.currentThread().getName());
        loggerInfo.setSpanceId(spanId);
        loggerInfo.setTraceId(traceId);
        loggerInfo.setDateTime(dateStr);
        loggerInfo.setClassName(this.getClass().getName());
        //发送消息
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("my-log", loggerInfo);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //发送失败的处理
                System.out.println(" - 生产者 发送消息失败：" + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //成功的处理

                System.out.println(" - 生产者 发送消息成功：" + stringObjectSendResult.toString());
            }
        });
        return obj2String;
    }
}
