package com.example.endpoint.controller;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @Autowired
  private DataSource dataSource;

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
}
