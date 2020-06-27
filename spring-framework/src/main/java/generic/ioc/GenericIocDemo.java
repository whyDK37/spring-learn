package generic.ioc;

import java.util.List;
import java.util.function.Consumer;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import pojo.Person;
import pojo.User;

/**
 * 泛型注入 demo
 */
public class GenericIocDemo {
  @Bean
  public UserMonitor userMonitor() {
    return new UserMonitor();
  }

  @Bean
  public PersonMonitor personMonitor() {
    return new PersonMonitor();
  }

  private List<Consumer<User>> users;

  @Autowired
  public void setUsers(List<Consumer<User>> users) {
    this.users = users;
  }

  @PostConstruct
  public void init() {
    // 注入的对象是 userMonitor
    users.forEach(System.out::println);
  }

  public static void main(String[] args) {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(GenericIocDemo.class);
    context.refresh();
    context.close();
  }

  public static class UserMonitor implements Consumer<User> {
    @Override
    public void accept(User user) {
    }
  }

  public static class PersonMonitor implements Consumer<Person> {
    @Override
    public void accept(Person user) {
    }
  }
}
