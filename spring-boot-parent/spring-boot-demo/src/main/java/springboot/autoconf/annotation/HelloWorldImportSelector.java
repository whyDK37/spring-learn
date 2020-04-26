package springboot.autoconf.annotation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import springboot.autoconf.configuration.HelloWorldConfiguration;

/**
 * HelloWorld {@link ImportSelector} 实现
 *
 * @author 小马哥
 * @since 2018/5/14
 */
public class HelloWorldImportSelector implements ImportSelector {

  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    return new String[]{HelloWorldConfiguration.class.getName()};
  }
}