package springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication()
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ServletComponentScan(basePackages = "springboot.web.servlet")
public class SpringBootServiceApplication {

  private static final Logger logger = LoggerFactory.getLogger(SpringBootServiceApplication.class);

  public static void main(String[] args) {
    SpringApplication springApplication = new SpringApplication(SpringBootServiceApplication.class);

    ConfigurableApplicationContext run = springApplication.run(args);

    System.out.println(run.getBean("why"));

  }
}