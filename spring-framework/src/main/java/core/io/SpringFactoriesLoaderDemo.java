package core.io;

import java.util.List;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.io.support.SpringFactoriesLoader;

/**
 * 用来加载 spring 工厂类，在 spring boot 也有使用，用来加载自动化配置
 */
public class SpringFactoriesLoaderDemo {

  public static void main(String[] args) {
    List<String> strings = SpringFactoriesLoader
        .loadFactoryNames(BeanFactory.class, Thread.currentThread().getContextClassLoader());
    System.out.println(strings.size());
    System.out.println(strings);

  }

}
