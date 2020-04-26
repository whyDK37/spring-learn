package springboot.autoconf.configuration;

import org.springframework.context.annotation.Configuration;
import springboot.autoconf.annotation.EnableHelloWorld;
import springboot.autoconf.condition.ConditionalOnSystemPropertyProperty;

@Configuration // Spring 模式注解装配
@EnableHelloWorld // Spring @Enable 模块装配
@ConditionalOnSystemPropertyProperty(name = "user.name", value = "bingb") // 条件装配
public class HelloWorldAutoConfiguration {

}