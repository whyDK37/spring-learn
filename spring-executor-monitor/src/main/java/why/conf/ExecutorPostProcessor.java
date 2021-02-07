package why.conf;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class ExecutorPostProcessor implements BeanPostProcessor {

  private Logger log = LoggerFactory.getLogger(ExecutorPostProcessor.class);

  CuratorFramework client;

  public ExecutorPostProcessor() {
    // 多个地址逗号隔开
    client = CuratorFrameworkFactory.builder().connectString("localhost:2181")
        .sessionTimeoutMs(1000)    // 连接超时时间
        .connectionTimeoutMs(1000) // 会话超时时间
        // 刚开始重试间隔为1秒，之后重试间隔逐渐增加，最多重试不超过三次
        .retryPolicy(new ExponentialBackoffRetry(1000, 3))
        .build();
    client.start();

    CloseableUtils.closeQuietly(client);//建议放在finally块中

    // 加载本地配置
  }

  Map<String, Executor> executorMap = new ConcurrentHashMap<>();


  public void change(String poolName){
    Executor executor = executorMap.get(poolName);
    // 修改

    // 最新的配置写入问题件
  }
  /**
   * 返回线程池包装类
   *
   * @param bean
   * @param beanName
   * @return
   * @throws BeansException
   */
  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    if (bean instanceof Executor) {
      // 包装线程池
      log.info("bean name:{}, bean:{}", beanName, bean);
      // 应用本地缓存的配置

      // 保存 name 和 对象 的关系
      executorMap.putIfAbsent(beanName, (Executor) bean);
    }
    return bean;
  }
}
