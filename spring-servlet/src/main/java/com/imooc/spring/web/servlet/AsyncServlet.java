package com.imooc.spring.web.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_SERVICE_UNAVAILABLE;

@WebServlet(asyncSupported = true, name = "asyncServlet", urlPatterns = "/async-servlet")
public class AsyncServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.isAsyncStarted()) {
            AsyncContext asyncContext = req.startAsync();
            asyncContext.addListener(new AsyncListener() {
                @Override
                public void onComplete(AsyncEvent asyncEvent) throws IOException {
                    println("执行完成");
                }

                @Override
                public void onTimeout(AsyncEvent asyncEvent) throws IOException {
                    HttpServletResponse servletResponse = (HttpServletResponse) asyncEvent.getSuppliedResponse();
                    servletResponse.setStatus(SC_SERVICE_UNAVAILABLE);
                    println("执行超时");
                }

                @Override
                public void onError(AsyncEvent asyncEvent) throws IOException {
                    println("执行错误");
                }

                @Override
                public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
                    println("开始执行");
                }
            });

            println("Hello,World");
        }
    }

    private static void println(Object object) {
        String threadName = Thread.currentThread().getName();
        System.out.println("AsyncServlet[" + threadName + "]: " + object);
    }
}
