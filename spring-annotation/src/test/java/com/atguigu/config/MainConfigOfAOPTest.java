package com.atguigu.config;

import com.atguigu.aop.MathCalculator;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class MainConfigOfAOPTest {

  @Test
  public void test01() {
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(MainConfigOfAOP.class);

    //1、不要自己创建对象
//		MathCalculator mathCalculator = new MathCalculator();
//		mathCalculator.div(1, 1);
    MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);

    mathCalculator.div(1, 0);

    applicationContext.close();
  }
}