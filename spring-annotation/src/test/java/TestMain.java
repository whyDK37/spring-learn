import annotation.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {

  @Test
  public void main() {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
        Config.class);

    for (String beanDefinitionName : context.getBeanFactory().getBeanDefinitionNames()) {
      System.out.println(beanDefinitionName);
    }

    // 两次获取 prototype 对象不是相同的
    Assertions.assertFalse(context.getBean("personProto") == context.getBean("personProto"));
  }
}
