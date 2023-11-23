package com.my.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/async")
public class AsyncController {

    @ResponseBody
    @GetMapping(value = "index")
    public void index(HttpServletRequest req, HttpServletResponse resp){

        System.out.println("threadname "+Thread.currentThread().getName());
        AsyncContext asyncContext = req.startAsync();
        asyncContext.setTimeout(1000*60*60);
        asyncContext.start(()->{

            new Thread(()->{
                try {
                    PrintWriter writer = null;
                    Thread.sleep(1000*10);
                    writer = resp.getWriter();
                    writer.write("end");
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
        for (int i = 0; i <10 ; i++) {
            writer.write("data:"+i);
            writer.flush();
        }
    }

}
