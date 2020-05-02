package springboot.webmvc.config;

import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import springboot.webmvc.method.support.PropertiesHandlerMethodArgumentResolver;
import springboot.webmvc.method.support.PropertiesHandlerMethodReturnValueHandler;

/**
 * Spring Web MVC 配置（类）
 *
 * @author 小马哥
 * @since 2018/5/20
 */
@Configuration
//配置了 EnableWebMvc，WebMvcAutoConfiguration 就不会加载了
//@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

  @Autowired
  private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

  @PostConstruct
  public void init() {
    // 获取当前 RequestMappingHandlerAdapter 所有的 Resolver 对象
    List<HandlerMethodArgumentResolver> resolvers = requestMappingHandlerAdapter
        .getArgumentResolvers();
    // 重新设置 Resolver 对象集合
    requestMappingHandlerAdapter.setArgumentResolvers(Lists
        .asList(new PropertiesHandlerMethodArgumentResolver(),
            resolvers.toArray(new HandlerMethodArgumentResolver[0])));

    // 获取当前 HandlerMethodReturnValueHandler 所有的 Handler 对象
    List<HandlerMethodReturnValueHandler> handlers = requestMappingHandlerAdapter
        .getReturnValueHandlers();
    requestMappingHandlerAdapter.setReturnValueHandlers(Lists
        .asList(new PropertiesHandlerMethodReturnValueHandler(),
            handlers.toArray(new HandlerMethodReturnValueHandler[0])));

  }


  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**").allowedOrigins("*");
  }

  //     <!--<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">-->
//        <!--<property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>-->
//        <!--<property name="prefix" value="/WEB-INF/jsp/"/>-->
//        <!--<property name="suffix" value=".jsp"/>-->
//    <!--</bean>-->
  @Bean
  public ViewResolver internalResourceViewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setViewClass(JstlView.class);
    viewResolver.setPrefix("/WEB-INF/jsp/");
    viewResolver.setSuffix(".jsp");
    // ThymeleafViewResolver Ordered.LOWEST_PRECEDENCE - 5
    viewResolver.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
    // 优先级高于 ThymeleafViewResolver
    // 配置 ViewResolver 的 Content-Type
    viewResolver.setContentType("text/xml;charset=UTF-8");
    return viewResolver;
  }

  @Override
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    configurer.favorParameter(true)
        .favorPathExtension(true);
  }


  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new HandlerInterceptor() {
      @Override
      public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
          Object handler) throws Exception {
        System.out.println("preHandle...");
        return true;
      }

      @Override
      public void postHandle(HttpServletRequest request, HttpServletResponse response,
          Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...");
      }
    });
  }
}
