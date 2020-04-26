package context;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring 应用事件引导类
 *
 * @author 小马哥
 * @since 2018/5/17
 */
public class SpringApplicationEventBootstrap {

  public static void main(String[] args) {
    // 创建上下文
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

    System.out.println("注册应用事件监听器，监听所有事件");
    context.addApplicationListener(event -> {
      System.out.println("监听到事件: " + event);
    });
    System.out.println("只监听 PayloadApplicationEvent 事件");
    context.addApplicationListener(
        (ApplicationListener<PayloadApplicationEvent>) payloadApplicationEvent -> System.out
            .println("payloadApplicationEvent = " + payloadApplicationEvent.getPayload()));

    // 启动上下文
    context.refresh();
    // 发送事件
    context.publishEvent("HelloWorld");
    context.publishEvent("2018");
    context.publishEvent(new ApplicationEvent("小马哥") {

    });

    // 关闭上下文
    context.close();
  }
}
