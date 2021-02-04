package why.conf;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池配置
 */
@Configuration
public class ExecutorConfig {

  @Value("${executor.channelDiscountInquiry.corePoolSize:4}")
  private Integer channelDiscountInquiryCorePoolSize;
  @Value("${executor.channelDiscountInquiry.maxPoolSize:10}")
  private Integer channelDiscountInquiryMaxPoolSize;
  @Value("${executor.channelDiscountInquiry.queueCapacity:10}")
  private Integer channelDiscountInquiryQueueCapacity;

  @Value("${executor.checkERP.corePoolSize:4}")
  private Integer checkERPCorePoolSize;
  @Value("${executor.checkERP.maxPoolSize:10}")
  private Integer checkERPMaxPoolSize;
  @Value("${executor.checkERP.queueCapacity:4}")
  private Integer checkERPQueueCapacity;


  @Value("${executor.count.corePoolSize:40}")
  private Integer countCorePoolSize;
  @Value("${executor.count.maxPoolSize:60}")
  private Integer countMaxPoolSize;
  @Value("${executor.count.queueCapacity:120}")
  private Integer countQueueCapacity;

  @Bean
  public ThreadPoolTaskExecutor inquiryExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setThreadNamePrefix("inquiry-");
    executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors() << 2 + 1);
    executor.setCorePoolSize(Runtime.getRuntime().availableProcessors() << 2 + 1);
    executor.setQueueCapacity(100);
    executor.setRejectedExecutionHandler(new CallerRunsPolicy());
    executor.initialize();
    return executor;
  }

  @Bean
  public Executor getComputeProductPriceExecutor() {
    ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
    threadPoolTaskExecutor.setThreadNamePrefix("computeProductPrice--");
    threadPoolTaskExecutor.setCorePoolSize(10);
    threadPoolTaskExecutor.setMaxPoolSize(10);
    threadPoolTaskExecutor.setQueueCapacity(200);
    threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    threadPoolTaskExecutor.initialize();
    return threadPoolTaskExecutor.getThreadPoolExecutor();
  }
}
