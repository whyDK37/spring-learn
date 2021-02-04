package why.conf;

import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class ExecutorPostProcessor implements BeanPostProcessor {

  private Logger log = LoggerFactory.getLogger(ExecutorPostProcessor.class);

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
    }
    return bean;
  }
}
