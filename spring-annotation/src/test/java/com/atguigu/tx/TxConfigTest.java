package com.atguigu.tx;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class TxConfigTest {

  @Test
  public void test01() {
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(TxConfig.class);

    UserService userService = applicationContext.getBean(UserService.class);

    userService.insertUser();
    applicationContext.close();
  }
}