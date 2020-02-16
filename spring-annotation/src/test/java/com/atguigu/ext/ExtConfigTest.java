package com.atguigu.ext;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class ExtConfigTest {

  @Test
  public void test01() {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
        ExtConfig.class);

    //发布事件；
    applicationContext.publishEvent(new ApplicationEvent("我发布的时间") {
    });

    applicationContext.close();
  }
}