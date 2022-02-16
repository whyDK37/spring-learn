package com.example.endpoint.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pojo.User;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

@RestController
public class TestController {

    int i = 0;
    @Resource
    private ExecutorService asyncExecutor;

    @RequestMapping(path = "/hello", method = {RequestMethod.GET})
    public Map<String, Object> hello(HttpServletResponse response, @ModelAttribute User user) {
        if (i++ % 3 == 1) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        Map<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("a", "hello");
        objectObjectHashMap.put("user", user);
        return objectObjectHashMap;
    }


    @RequestMapping("/async")
    public String async() {
        asyncExecutor.submit(() -> System.out.println("async print..."));
        return "async";
    }
}
