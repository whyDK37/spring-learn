package com.atguigu.test;

import com.atguigu.ext.ExtConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_Ext {

    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExtConfig.class);


        //发布事件；
        applicationContext.publishEvent(new ApplicationEvent("我发布的时间") {
        });

        applicationContext.close();
    }

}
