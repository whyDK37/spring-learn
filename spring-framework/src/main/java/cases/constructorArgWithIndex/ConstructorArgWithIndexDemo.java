package cases.constructorArgWithIndex;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * https://mp.weixin.qq.com/s/bIpnXFTXuNq5WzBlRdVVNQ
 * <p>文章修复了一个代码的问题，当时我测试的时候并没有发现修改之前会有异常，可能是修复之后使代码更加合理吧。
 * <p>文章修复的问题 5.2.7 发布了，issues: https://github.com/spring-projects/spring-framework/issues/25130
 *
 * @author wanghuanyu
 */
public class ConstructorArgWithIndexDemo {

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
        "classpath:META-INF/cases/constructorArgWithIndex.xml");

    DmzService service = applicationContext.getBean("dmzService", DmzService.class);
    System.out.println(service);

  }
}
