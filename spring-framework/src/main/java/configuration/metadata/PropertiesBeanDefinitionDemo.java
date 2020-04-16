package configuration.metadata;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import pojo.User;

public class PropertiesBeanDefinitionDemo {

  public static void main(String[] args) {

    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

    PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(beanFactory);
    ResourceLoader resourceLoader = new DefaultResourceLoader();
    Resource resource = resourceLoader.getResource("META-INF/user-config.properties");
    EncodedResource encodedResource = new EncodedResource(resource, ("UTF-8"));
    int i = reader.loadBeanDefinitions(encodedResource);
    System.out.println("加载了多少个bean：" + i);

    User user = beanFactory.getBean("user", User.class);
    System.out.println(user);
  }

}
