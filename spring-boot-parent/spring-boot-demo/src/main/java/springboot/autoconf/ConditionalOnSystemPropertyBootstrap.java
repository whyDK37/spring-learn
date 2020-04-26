package springboot.autoconf;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import springboot.autoconf.condition.ConditionalOnSystemPropertyProperty;

/**
 * 系统属性条件引导类
 *
 * @author 小马哥
 * @since 2018/5/15
 */
public class ConditionalOnSystemPropertyBootstrap {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = new SpringApplicationBuilder(
        ConditionalOnSystemPropertyBootstrap.class)
        .web(WebApplicationType.NONE)
        .run(args);
    // 通过名称和类型获取 helloWorld Bean
    String helloWorld = context.getBean("helloWorld", String.class);

    System.out.println("helloWorld Bean : " + helloWorld);

    // 关闭上下文
    context.close();
  }

  @Bean
  @ConditionalOnSystemPropertyProperty(name = "user.name", value = "bingb")
  public String helloWorld() {
    return "Hello,World 小马哥";
  }
}




