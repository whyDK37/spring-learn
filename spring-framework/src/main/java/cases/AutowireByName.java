package cases;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutowireByName {

  public static void main(String[] args) {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
        "classpath:META-INF/cases/autowire-by-name.xml");

    Service service = applicationContext.getBean("service", Service.class);
    System.out.println(service);
    System.out.println(service.store);
  }
}
