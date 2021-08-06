package why;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@EnableCaching
public class SpringBootServiceApplication {

  private static final Logger logger = LoggerFactory.getLogger(SpringBootServiceApplication.class);

  public static void main(String[] args) {
    SpringApplication springApplication = new SpringApplication(SpringBootServiceApplication.class);

    ConfigurableApplicationContext run = springApplication.run(args);

    ConfigurableEnvironment environment = run.getEnvironment();
    System.out
        .println("environment.getProperty(\"why.env\") = " + environment.getProperty("why.env"));

  }
}