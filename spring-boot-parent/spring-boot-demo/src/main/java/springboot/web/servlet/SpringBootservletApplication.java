package springboot.web.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication()
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ServletComponentScan(basePackages = "springboot.web.servlet")
public class SpringBootservletApplication {

  private static final Logger logger = LoggerFactory.getLogger(SpringBootservletApplication.class);

  public static void main(String[] args) {
    SpringApplication springApplication = new SpringApplication(SpringBootservletApplication.class);

    springApplication.run(args);

  }
}