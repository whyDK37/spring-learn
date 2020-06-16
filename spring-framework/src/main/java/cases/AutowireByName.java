package cases;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 修改 xml 中或者 AutowiredConfig 文件中的 Primary，Service 会注入不同的 bean。
 * @author wanghuanyu
 */
public class AutowireByName {

  public static void main(String[] args) {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
        "classpath:META-INF/cases/autowire-by-name.xml");

    Service service = applicationContext.getBean("service", Service.class);
    System.out.println(service);
    System.out.println(service.store);
  }
}
