package core.io.resource;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

public class ResourceLoaderDemo implements ResourceLoaderAware {

  @Autowired
  private ResourceLoader resourceLoader;

  @PostConstruct
  public void init() {
    System.out.println(resourceLoader);
  }

  public static void main(String[] args) {
    // 创建上下文
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(ResourceLoaderDemo.class);

    // 启动上下文
    context.refresh();

    System.out.println("context = " + context);
    // 关闭上下文
    context.close();
  }

  @Override
  public void setResourceLoader(ResourceLoader resourceLoader) {
    System.out.println("set resource loader:" + resourceLoader);
  }
}
