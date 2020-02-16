package demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BootApplication implements ApplicationContextAware {

  @Autowired
  private ApplicationContext applicationContext;

  public static void main(String[] args) {
    SpringApplication springApplication = new SpringApplication(BootApplication.class);
    springApplication.run(args);
  }


  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

}
