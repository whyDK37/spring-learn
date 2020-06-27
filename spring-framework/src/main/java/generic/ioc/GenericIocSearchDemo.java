package generic.ioc;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.function.Consumer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ResolvableType;
import pojo.Person;
import pojo.User;

/**
 * 泛型注入 demo
 */
public class GenericIocSearchDemo {

  @Bean
  public UserMonitor userMonitor() {
    return new UserMonitor();
  }

  @Bean
  public PersonMonitor personMonitor() {
    return new PersonMonitor();
  }

  public Consumer<User> userConsumer;

  public static void main(String[] args) throws NoSuchFieldException {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(GenericIocSearchDemo.class);
    context.refresh();

    // 可以通过 ResolvableType 的方式，实现泛型查找。
    ResolvableType field = ResolvableType
        .forField(GenericIocSearchDemo.class.getField("userConsumer"));
    Type type = field.getType();
    field.resolve();

    String[] beanNamesForType = context.getBeanNamesForType(field);
    System.out.println(Arrays.toString(beanNamesForType));

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
