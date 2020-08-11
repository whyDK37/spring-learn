package springboot.autoconf.condition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wanghuanyu10
 */
@Configuration
@ConditionalOnProperty(prefix = "why", name = "config", havingValue = "true", matchIfMissing = false)
public class WhyConfig {

  @Bean
  public String why() {
    return "why";
  }

}
