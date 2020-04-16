package configuration.metadata;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import pojo.User;

public class BeanConfigurationMetadata {

  public static void main(String[] args) {

    BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
        .genericBeanDefinition(User.class);
    beanDefinitionBuilder.addPropertyValue("name", "why");
    AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
    // 附加属性，不影响 bean populate，initialize
    beanDefinition.setAttribute("name", "abc");
    // 辅助信息，标记 beanDefinition 来源
    beanDefinition.setSource(BeanConfigurationMetadata.class);

    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
      @Override
      public Object postProcessAfterInitialization(Object bean, String beanName)
          throws BeansException {
        if ("user".equals(beanName)) {
          User user = (User) bean;
          BeanDefinition bd = beanFactory.getBeanDefinition("user");
          System.out
              .println(String.format("修改 name，%s->%s", user.getName(), bd.getAttribute("name")));
          user.setName((String) bd.getAttribute("name"));
        }
        return bean;
      }
    });
    beanFactory.registerBeanDefinition("user", beanDefinition);

    User user = beanFactory.getBean("user", User.class);
    System.out.println(user);
  }

}
