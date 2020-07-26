package event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 */
public class HierarchicalSpringEventDemo {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext parent = new AnnotationConfigApplicationContext();
    parent.setId("parent");

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.setId("context");
    context.setParent(parent);

    // 方法一： 面向接口注册事件

    // 方法二： 基于 注解
    parent.refresh();
    context.refresh();

    context.close();
  }
}
