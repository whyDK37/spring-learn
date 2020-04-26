package springboot.autoconf;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import springboot.autoconf.annotation.EnableHelloWorld;

/**
 * @author 小马哥
 * @since 2018/5/14
 */
@EnableHelloWorld
public class EnableHelloWorldBootstrap {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = new SpringApplicationBuilder(
        EnableHelloWorldBootstrap.class)
        .web(WebApplicationType.NONE)
        .run(args);

    // helloWorld Bean 是否存在
    String helloWorld =
        context.getBean("helloWorld", String.class);

    System.out.println("helloWorld Bean : " + helloWorld);

    // 关闭上下文
    context.close();
  }
}



