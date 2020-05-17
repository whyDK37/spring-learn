package core.io.resource;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * 路径匹配 demo
 */
public class PathMatchingResourcePatternResolverDemo {

  public static void main(String[] args) throws IOException {
    // E:\workspace\spring-learn\spring-framework\src\main\java\core\io\PathMatchingResourcePatternResolverDemo.java
    String userDir = System.getProperty("user.dir");

    PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver(
        new FileSystemResourceLoader());

    // 自定义 pathMatcher
    patternResolver.setPathMatcher(new AntPathMatcher() {
      @Override
      public boolean match(String pattern, String path) {
        return path.endsWith("pom.xml");
      }
    });

    Resource[] resources = patternResolver
        .getResources(userDir + "/*/*.xml");

    System.out.println("resources.length = " + resources.length);
    for (Resource resource : resources) {
      System.out.println(resource.getURL());
    }
  }

}
