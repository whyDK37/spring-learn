package event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * spring 事件
 */
@EnableAsync //激活异步执行
public class ApplicationListenerDemo {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(ApplicationListenerDemo.class);

    // 方法一： 面向接口注册事件
    context.addApplicationListener(event -> {
      print(event.toString());
    });
    // 方法二： 基于 注解
    context.refresh();

    context.close();
  }

  /**
   * 注解的事件监听，没有方法和接口的约束，支持泛型，异步，顺序
   *
   * @param event
   */
  @EventListener
  @Async
  public void onApplicationEventAsync1(ContextRefreshedEvent event) {
    print("async @EventListener：ContextRefreshedEvent");
  }


  @EventListener
  @Order(2)
  public void onApplicationEvent2(ContextClosedEvent event) {
    String str = "@EventListener：ContextClosedEvent 2";
    print(str);
  }

  @EventListener
  @Order(1)
  public void onApplicationEvent1(ContextClosedEvent event) {
    String str = "@EventListener：ContextClosedEvent 1";
    print(str);
  }


  public static void print(String str) {
    System.out.println(String.format("%s-%s", Thread.currentThread().getName(), str));
  }

}
