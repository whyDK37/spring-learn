package why;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication()
public class App {

  public static void main(String[] args) {
    SpringApplication springApplication = new SpringApplication(App.class);

    ConfigurableApplicationContext run = springApplication.run(args);


    // 多个地址逗号隔开
    CuratorFramework client = CuratorFrameworkFactory.builder().connectString("localhost:2181")
        .sessionTimeoutMs(1000)    // 连接超时时间
        .connectionTimeoutMs(1000) // 会话超时时间
        // 刚开始重试间隔为1秒，之后重试间隔逐渐增加，最多重试不超过三次
        .retryPolicy(new ExponentialBackoffRetry(1000, 3))
        .build();
    client.start();

    CloseableUtils.closeQuietly(client);//建议放在finally块中
  }

}
