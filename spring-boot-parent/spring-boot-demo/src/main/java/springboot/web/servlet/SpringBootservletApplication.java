package springboot.web.servlet;

import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootApplication()
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ServletComponentScan(basePackages = "springboot.web.servlet")
public class SpringBootservletApplication {

  private static final Logger logger = LoggerFactory.getLogger(SpringBootservletApplication.class);

  public static void main(String[] args) {
    SpringApplication springApplication = new SpringApplication(SpringBootservletApplication.class);
    springApplication.run(args);
  }

  @Bean
  @Order(Ordered.HIGHEST_PRECEDENCE)
  public ServletRegistrationBean<AsyncServlet> asyncServletServletRegistrationBean(){
    ServletRegistrationBean<AsyncServlet> registrationBean =  new ServletRegistrationBean<>(new AsyncServlet(),"/");
    registrationBean.setName("MyAsyncServlet");
    return registrationBean;
  }

  @Bean
  public ServletContextInitializer servletContextInitializer() {
    return servletContext -> {
      CharacterEncodingFilter filter = new CharacterEncodingFilter();
      FilterRegistration.Dynamic registration = servletContext.addFilter("filter", filter);
      registration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/");
    };
  }
}