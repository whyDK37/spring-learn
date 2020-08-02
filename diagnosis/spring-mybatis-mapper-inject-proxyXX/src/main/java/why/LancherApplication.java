package why;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import why.manager.UserManager;
import why.manager.UserManagerImpl;

@MapperScan("why.mapper")
@SpringBootApplication
public class LancherApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext run = SpringApplication.run(LancherApplication.class, args);

    UserManager userManager = run.getBean("userManager", UserManagerImpl.class);
    System.out.println(userManager);
  }

}
