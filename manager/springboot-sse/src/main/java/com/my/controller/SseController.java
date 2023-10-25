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

@Controller
@RequestMapping("/sse")
public class SseController {

    private SseEmitter emitter;
    @GetMapping("index")
    public String index(){
        return "index";
    }
    @GetMapping("index2")
    public String index2(){
        return "index2";
    }
    @GetMapping("index3")
    public String index3(){
        return "index3";
    }
    @ResponseBody
    @GetMapping(value = "data",produces = "text/event-stream;charset=utf-8")
    public String data(){

        System.out.println("...........");
        Date date = new Date();

//        String data = "data:" + date.getTime() + "\n";
//        data +="retry: 10000\n";
//        data += "dffata:aa" + date.getTime() + "\n\n";




        String data="data: {\n";
        data+="data: \"foo\": \"bar\",\n";
        data+="data: \"baz\", 555\n";
        data+="data: }\n\n";
        return data;
    }

    @ResponseBody
    @GetMapping(value = "data1",produces = "text/event-stream;charset=utf-8")
    public void data1(HttpServletRequest req, HttpServletResponse resp){

        System.out.println("threadname "+Thread.currentThread().getName());
        AsyncContext asyncContext = req.startAsync();
        asyncContext.setTimeout(1000*60*60);
        asyncContext.start(()->{

            new Thread(()->{
                try {
                    PrintWriter writer = null;
                    Thread.sleep(1000*10);
                    writer = resp.getWriter();
                    writer.write("data: }\n\n");
                    System.out.println("threadname2 "+Thread.currentThread().getName());
                    asyncContext.complete();
                } catch (IOException | InterruptedException e) {
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
        String data="data: {\n";
        data+="data: \"foo\": \"bar\",\n";
        data+="data: \"baz\", 555\n";
        writer.write(data);
        writer.flush();
    }

    @ResponseBody
    @GetMapping(path="/data2", produces= MediaType.TEXT_EVENT_STREAM_VALUE)
    public  SseEmitter data2() {
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
