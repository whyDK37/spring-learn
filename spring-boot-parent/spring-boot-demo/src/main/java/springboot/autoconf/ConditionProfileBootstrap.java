package springboot.autoconf;

import java.util.function.Function;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

/**
 * 使用 profile 的方式条件装配
 */
@SpringBootApplication(scanBasePackages = "springboot.autoconf")
public class ConditionProfileBootstrap {


  public static void main(String[] args) {
    ConfigurableApplicationContext context = new SpringApplicationBuilder(
        ConditionProfileBootstrap.class)
        .web(WebApplicationType.NONE)
        .profiles("jdk7")
        .run(args);

    // CalculateService Bean 是否存在
    Function<String, String> calculateService = context.getBean(Function.class);
    System.out.println("calculateService.apply(\"name\") = " + calculateService.apply("name"));

    // 关闭上下文
    context.close();
  }

  @Bean
  @Profile("jdk7")
  public Function<String, String> jdk7() {
    return s -> s + " jdk7";
  }

  @Bean
  @Profile("jdk8")
  public Function<String, String> jdk8() {
    return s -> s + " jdk8";
  }
}
