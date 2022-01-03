package com.example.endpoint.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutorService;

@RestController
public class TestController {

    int i = 0;
    @Resource
    private ExecutorService asyncExecutor;

    @RequestMapping("/hello")
    public String hello(HttpServletResponse response) {
        if (i++ % 3 == 1) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return "hello";
    }


    @RequestMapping("/async")
    public String async() {
        asyncExecutor.submit(() -> System.out.println("async print..."));
        return "async";
    }
}
