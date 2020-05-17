package ioc;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import pojo.User;

public class IOCSearch {

  public static void main(String[] args) {
    DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
    XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
    reader.loadBeanDefinitions(new ClassPathResource("META-INF/ioc-search.xml"));

    // bring in some property values from a Properties file
    PropertySourcesPlaceholderConfigurer cfg = new PropertySourcesPlaceholderConfigurer();
    cfg.setLocation(new ClassPathResource("META-INF/user.properties"));

    // now actually do the replacement
    cfg.postProcessBeanFactory(factory);

    System.out.println("factory.getBeanDefinitionCount() = " + factory.getBeanDefinitionCount());
    User user = factory.getBean("user", User.class);
    System.out.println(user);
  }

}
