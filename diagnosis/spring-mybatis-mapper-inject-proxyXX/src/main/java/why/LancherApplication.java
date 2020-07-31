package why;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("why.mapper")
@SpringBootApplication
public class LancherApplication {

  public static void main(String[] args) {
    SpringApplication.run(LancherApplication.class, args);
  }

}
