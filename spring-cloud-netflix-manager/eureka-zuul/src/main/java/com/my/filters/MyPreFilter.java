package com.my.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MyPreFilter  extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        System.out.println(String.format("%s AccessUserNameFilter request to %s", request.getMethod(), request.getRequestURL().toString()));

        String username = request.getParameter("username");// 获取请求的参数
//        if(null != username && username.equals("chhliu")) {// 如果请求的参数不为空，且值为chhliu时，则通过
//            ctx.setSendZuulResponse(true);// 对该请求进行路由
//            ctx.setResponseStatusCode(200);
//            ctx.set("isSuccess", true);// 设值，让下一个Filter看到上一个Filter的状态
//            return null;
//        }else{
//            ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
//            ctx.setResponseStatusCode(401);// 返回错误码
//            ctx.setResponseBody("{\"result\":\"username is not correct!\"}");// 返回错误内容
//            ctx.set("isSuccess", false);
//            return null;
//        }


//        ctx.setSendZuulResponse(false);
//        ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
//        String result = "dsdsds";
//        ctx.setResponseBody(result);


        return  null;
    }
}
