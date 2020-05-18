package core.io.resource;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.Resource;
import pojo.User;

@PropertySource(value = {"classpath:META-INF/default.properties",
    "classpath:META-INF/user.properties"})
@Configuration
public class InjectionResourceDemo {

  @Value("${no:not defined}")
  private String noDefine;

  @Value("${catalog.name}")
  private String catalog;

  @Value("#{systemProperties['os.name']}")
  private String osName; // 注入操作系统属性

  @Value("classpath:/META-INF/default.properties")
  private Resource defaultProperty;
  @Value("classpath*:/META-INF/*.properties")
  private Resource[] resources;
  @Value("classpath*:/META-INF/*.properties")
  private List<Resource> resourceCollection;

  @Value("http://www.baidu.com")
  private Resource baidu; // 注入URL资源

  @Value("#{'${demo.list}'.split(',')}")
  private List<String> list;

  @Value("#{{'Thriller': 100, 'Comedy': 300}}")
  Map<String, Integer> countOfMoviesPerCatalog;

  @Value("#{${demo.map}}")
  private Map<String, String> maps;

  @Bean
  public User user(@Value("${name}") String name) {
    return new User().setName(name);
  }

  @PostConstruct
  public void init() throws IOException {
    System.out.println("noDefine = " + noDefine);
    System.out.println("catalog = " + catalog);
    System.out.println("osName = " + osName);
    System.out.println(defaultProperty);
    System.out.println("------------------");
    for (Resource resource : resources) {
      System.out.println(resource);
    }
    System.out.println("------------------");
    for (Resource resource : resourceCollection) {
      System.out.println("resource.getClass() = " + resource.getClass());
      System.out.println(resource);
    }
    System.out.println("------------------");
    System.out.println("baidu = " + baidu);

    System.out.println("------------------");
    System.out.println(list);
    System.out.println("------------------");
    System.out.println("countOfMoviesPerCatalog = " + countOfMoviesPerCatalog);
    System.out.println(maps);
  }

  public static void main(String[] args) {
    // 创建上下文
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(InjectionResourceDemo.class);

    // 启动上下文
    context.refresh();

    final ConfigurableEnvironment environment = context.getEnvironment();
    final MutablePropertySources propertySources = environment.getPropertySources();
    System.out
        .println("environment.getProperty(\"demo.map\") = " + environment.getProperty("demo.map"));

    // 关闭上下文
    context.close();
  }
}
