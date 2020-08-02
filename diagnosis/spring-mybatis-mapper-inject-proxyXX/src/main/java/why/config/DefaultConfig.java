package why.config;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class DefaultConfig {


//  @Bean
//  public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
//    DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//    defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
//    defaultAdvisorAutoProxyCreator.setOrder(Ordered.HIGHEST_PRECEDENCE);
//    return defaultAdvisorAutoProxyCreator;
//  }
//  @Bean
//  public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator2() {
//    DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//    defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
//    defaultAdvisorAutoProxyCreator.setOrder(Ordered.HIGHEST_PRECEDENCE);
//    return defaultAdvisorAutoProxyCreator;
//  }
}
