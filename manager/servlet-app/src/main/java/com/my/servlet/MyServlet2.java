package com.my.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="FirstServlet2",urlPatterns="/first2",loadOnStartup = -2)
public class MyServlet2 extends HttpServlet {
    public MyServlet2(){

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("请求成功2");
    }
}
