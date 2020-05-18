package core.io.resource;

import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = {"classpath:META-INF/default.properties",
    "classpath:META-INF/user.properties"})
@Configuration
public class InjectionResourceDemo2 {

//  @Value("classpath:/META-INF/default.properties")
  private Resource defaultProperty;

  @Value("${id}")
  Integer id;

  @PostConstruct
  public void init() throws IOException {
    System.out.println("defaultProperty = " + defaultProperty);
  }

  public static void main(String[] args) {
    // 创建上下文
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(InjectionResourceDemo2.class);

    // 启动上下文
    context.refresh();

    System.out.println(context.getEnvironment().resolvePlaceholders("${name}-${id}"));

    // 关闭上下文
    context.close();
  }

  public void setDefaultProperty(Resource defaultProperty) {
    this.defaultProperty = defaultProperty;
  }

}
