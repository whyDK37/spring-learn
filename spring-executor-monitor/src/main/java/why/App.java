package why;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication()
public class App {

  public static void main(String[] args) {
    SpringApplication springApplication = new SpringApplication(App.class);

    ConfigurableApplicationContext run = springApplication.run(args);
  }

}
