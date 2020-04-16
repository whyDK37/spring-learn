package configuration.metadata;

import java.util.Map;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import pojo.User;

@ImportResource("classpath:/META-INF/ioc-search.xml")
@Import(User.class)
@PropertySource("classpath:/META-INF/user-config.properties")
@PropertySource("classpath:/META-INF/user-config.properties")
public class AnnotatedSpringIoCConfigMetadataDemo {

  @Bean
  public User configUser(@Value("${user.id}") Integer id,
      @Value("${user.name}") String name) {
    User user = new User();
    user.setId(id);
    user.setName(name);
    return user;
  }


  public static void main(String[] args) {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(AnnotatedSpringIoCConfigMetadataDemo.class);

    context.refresh();

    Map<String, User> beansOfType = context.getBeansOfType(User.class);

    for (Entry<String, User> stringUserEntry : beansOfType.entrySet()) {
      System.out.println(stringUserEntry.getKey() + "=" + stringUserEntry.getValue());
      System.out.println();
    }

    context.close();
  }

}
