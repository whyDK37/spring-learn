package springboot.webmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Web MVC 引导类
 *
 * @author 小马哥
 * @since 2018/5/21
 */
@SpringBootApplication(scanBasePackages = "springboot.webmvc")
public class SpringBootWebMvcBootstrap {

  public static void main(String[] args) {
    SpringApplication springApplication = new SpringApplication(SpringBootWebMvcBootstrap.class);

    springApplication.run(args);
  }

}
