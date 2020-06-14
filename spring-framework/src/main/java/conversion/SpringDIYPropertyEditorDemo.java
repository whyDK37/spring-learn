package conversion;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.User;

public class SpringDIYPropertyEditorDemo {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
        "META-INF/conversion/property-editor.xml");
    User user = context.getBean("user", User.class);
    // context 属性会注入
    System.out.println(user);
    context.close();
  }

}
