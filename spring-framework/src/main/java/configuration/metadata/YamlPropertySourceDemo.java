package configuration.metadata;

import configuration.metadata.YamlPropertySourceDemo.YamlPropertySourceFactory;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import pojo.CityEnum;
import pojo.User;

@PropertySource(name = "yml", value = "classpath:/META-INF/user.yaml",
    factory = YamlPropertySourceFactory.class)
public class YamlPropertySourceDemo {

  @Bean
  public User configUser(@Value("${user.id}") Integer id,
      @Value("${user.name}") String name,
      @Value("${user.city}") CityEnum city) {
    User user = new User();
    user.setId(id);
    user.setName(name);
    user.setCity(city);
    return user;
  }

  @Bean
  public YamlMapFactoryBean yamlMap() {
    YamlMapFactoryBean yamlMapFactoryBean = new YamlMapFactoryBean();
    yamlMapFactoryBean.setResources(new ClassPathResource("META-INF/user.yaml"));
    return yamlMapFactoryBean;
  }


  public static void main(String[] args) {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(YamlPropertySourceDemo.class);

    context.refresh();

    Map<String, User> beansOfType = context.getBeansOfType(User.class);
    for (Entry<String, User> stringUserEntry : beansOfType.entrySet()) {
      System.out.println(stringUserEntry.getKey());
      System.out.println(stringUserEntry.getValue());
    }

    Map<String, Object> yamlMap = context.getBean("yamlMap", Map.class);
    System.out.println("yamlMap = " + yamlMap);

    context.close();
  }

  public static class YamlPropertySourceFactory implements PropertySourceFactory {

    @Override
    public org.springframework.core.env.PropertySource<?> createPropertySource(String name,
        EncodedResource resource) throws IOException {

      YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
      yamlPropertiesFactoryBean.setResources(resource.getResource());
      Properties yamlProperties = yamlPropertiesFactoryBean.getObject();
      return new PropertiesPropertySource(name, yamlProperties);
    }
  }

}
