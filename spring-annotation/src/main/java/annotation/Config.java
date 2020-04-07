package annotation;

import annotation.condition.LinuxCondition;
import annotation.condition.WindowsCondition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import pojo.Person;

/**
 *
 */
@Configuration
@ComponentScan(value = "annotation")
public class Config {

  @Lazy// 延迟加载
  @Bean
  public Person person() {
    return Person.builder().name("abc").age(12).build();
  }

  /**
   * 每次都会生成新的对象
   */
  @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  @Bean
  public Person personProto() {
    return Person.builder().name("abc proto").age(12).build();
  }

  // - @Conditional 可根据条件加载类或者bean，spring boot中大量使用这个注解实现自动配置。
  @Conditional({WindowsCondition.class})
  @Bean
  public Person personWindows() {
    return Person.builder().name("windows").age(12).build();
  }

  @Conditional({LinuxCondition.class})
  @Bean
  public Person personLinux() {
    return Person.builder().name("Linux").age(12).build();
  }
}
