package com.example.endpoint.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @Autowired
  private DataSource dataSource;

  @Resource
  private ExecutorService asyncExecutor;

  @RequestMapping("/hello")
  public String hello() {
    try {
      Connection connection = dataSource.getConnection();
      connection.close();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return "hello";
  }


  @RequestMapping("/async")
  public String async() {
    asyncExecutor.submit(() -> System.out.println("async print..."));
    return "async";
  }
}
