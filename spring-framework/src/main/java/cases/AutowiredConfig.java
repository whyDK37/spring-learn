package cases;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AutowiredConfig {

  @Bean
  @Primary
  public Store fileStore(){
    return new FileStore();
  }
}
