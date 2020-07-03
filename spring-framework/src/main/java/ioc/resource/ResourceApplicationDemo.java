package ioc.resource;

import generic.ioc.GenericIocDemo;
import java.util.function.Consumer;
import javax.annotation.Resource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import pojo.Person;
import pojo.User;

/**
 * @author why
 */
public class ResourceApplicationDemo {
//  @Bean
//  public GenericIocDemo.UserMonitor userMonitor() {
//    return new GenericIocDemo.UserMonitor();
//  }

  @Resource
  Consumer<User> userMonitor;

  public static void main(String[] args) {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(ResourceApplicationDemo.class);
    context.refresh();

    final ResourceApplicationDemo bean = context.getBean(ResourceApplicationDemo.class);
    System.out.println(bean);
    System.out.println(bean.userMonitor);

    context.close();
  }

  public static class UserMonitor implements Consumer<User> {
    @Override
    public void accept(User user) {
    }
  }
}
