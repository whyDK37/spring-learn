package com.atguigu.config;

import com.atguigu.bean.Boss;
import com.atguigu.bean.Car;
import com.atguigu.bean.Color;
import com.atguigu.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class MainConifgOfAutowiredTest {


  @Test
  public void test01() {
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(MainConifgOfAutowired.class);

    BookService bookService = applicationContext.getBean(BookService.class);
    System.out.println("bookService = " + bookService);

    //BookDao bean = applicationContext.getBean(BookDao.class);
    //System.out.println(bean);

    Boss boss = applicationContext.getBean(Boss.class);
    System.out.println("boss = " + boss);
    Car car = applicationContext.getBean(Car.class);
    System.out.println("car = " + car);
    assertTrue(boss.getCar() == car);

    Color color = applicationContext.getBean(Color.class);
    System.out.println("color = " + color);
    System.out.println("applicationContext = " + applicationContext);

    applicationContext.close();
  }
}