package springboot.autoconf;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * <pre>
 * 条件判断： user.name == "Mercy"
 * 模式注解： @Configuration @Enable
 * 模块： @EnableHelloWorld -> HelloWorldImportSelector -> HelloWorldConfiguration - > helloWorld
 *
 * </pre>
 */
@EnableAutoConfiguration
public class EnableAutoConfigurationBootstrap {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = new SpringApplicationBuilder(
        EnableAutoConfigurationBootstrap.class)
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