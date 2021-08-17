package com.my.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="Servlet3Demo",urlPatterns="/Servlet3Demo")
public class MyServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.service(req, resp);
        System.out.println("Servlet3Demo....");
        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.write("Servlet3Demo".getBytes());
        outputStream.close();
    }
}
