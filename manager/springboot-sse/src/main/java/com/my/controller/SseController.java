package com.my.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * sse 只有在连接断开了 才会从新发送请求
 */
@Controller
@RequestMapping("/sse")
public class SseController {

    private SseEmitter emitter;
    @GetMapping("sync1")
    public String sync1(){
        return "sync1";
    }
    @GetMapping("sync2")
    public String sync2(){
        return "sync2";
    }
    @GetMapping("async1")
    public String async1(){
        return "async1";
    }
    @GetMapping("async2")
    public String async2(){
        return "async2";
    }
    @GetMapping(value = "syncData1")
    public void syncData1(HttpServletRequest req, HttpServletResponse resp){

        resp.setHeader("Content-Type","text/event-stream;charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            String data="data:"+new Date().getTime()+ "\n\n";
            writer.write(data);
            writer.flush();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

     @ResponseBody
     @GetMapping(value = "syncData2",produces = "text/event-stream;charset=utf-8")
    public String syncData2(){

        System.out.println("...........");
        Date date = new Date();

//        String data = "data:" + date.getTime() + "\n";
//        data +="retry: 10000\n";
//        data += "dffata:aa" + date.getTime() + "\n\n";

        String data="data: {\n";
        data+="data: \"foo\": \"bar\",\n";
        data+="data: \"baz\","+date.getTime()+" 555\n";
        data+="data: }\n\n";
        return data;
    }


    @ResponseBody
    @GetMapping(value = "asyncData1",produces = "text/event-stream;charset=utf-8")
    public void asyncData1(HttpServletRequest req, HttpServletResponse resp){

        System.out.println("threadname "+Thread.currentThread().getName());
        AsyncContext asyncContext = req.startAsync();
        asyncContext.setTimeout(0);
        asyncContext.start(()->{

            new Thread(()->{
                try {
                    PrintWriter writer = null;
                    Thread.sleep(1000*10);
                    System.out.println("threadname2 "+Thread.currentThread().getName());
                    asyncContext.complete();
                } catch ( InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        });
        PrintWriter writer = null;
        try {
            resp.setHeader("Content-Type","text/event-stream;charset=utf-8");
            writer = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            String data="data:"+new Date().getTime()+ "\n\n";
            writer.write(data);
            writer.flush();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @ResponseBody
    @GetMapping(path="/asyncData2", produces= MediaType.TEXT_EVENT_STREAM_VALUE)
    public  SseEmitter asyncData2() {
        if(emitter==null){
            emitter = new SseEmitter(1000*60*6L);
        }


//        new Thread(()->{
//            try {
//
//                Thread.sleep(1000*10);
//                System.out.println("/////////////////////");
//                emitter.complete();
//            } catch (Exception e) {
//                emitter.completeWithError(e);
//            }
//        }).start();
        return emitter;
    }

    @ResponseBody
    @GetMapping(path="/push", produces= MediaType.TEXT_EVENT_STREAM_VALUE)
    public  void push(String msg) {
        try {
            if(msg.equals("close")){
                emitter.complete();
                emitter = new SseEmitter(1000*60*6L);
            }else {
                emitter.send(msg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
