package springboot.autoconf.condition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wanghuanyu10
 */
@Configuration
public class WhyDefaultConfig {

  @Bean
  @ConditionalOnMissingBean(name = "why")
  public String why() {
    return "default why";
  }

}
