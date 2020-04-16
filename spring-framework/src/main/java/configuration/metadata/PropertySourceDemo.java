package configuration.metadata;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;
import pojo.User;

@PropertySource("classpath:/META-INF/user-config.properties")
public class PropertySourceDemo {

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
    // 扩展 Environment 中的 PropertySources
    // 增加一个优先级高的 PropertySource，优先级高的配置生效
    Map<String, Object> propertySource = new HashMap<>();
    propertySource.put("user.name", "why java code");
    org.springframework.core.env.PropertySource propertySource1 = new MapPropertySource("first-p",
        propertySource);
    context.getEnvironment().getPropertySources().addFirst(propertySource1);
    context.register(PropertySourceDemo.class);

    context.refresh();

    Map<String, User> beansOfType = context.getBeansOfType(User.class);

    for (Entry<String, User> stringUserEntry : beansOfType.entrySet()) {
      System.out.println(stringUserEntry.getKey() + "=" + stringUserEntry.getValue());
    }
    System.out.println(context.getEnvironment().getPropertySources());
    System.out.println(context.getEnvironment());

    context.close();
  }

}
