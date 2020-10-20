package com.example.endpoint.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.jvm.ExecutorServiceMetrics;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ExecutorConf {


  @Value("${thread.async.pool-size:40}")
  private int asyncPoolSize;
  @Value("${thread.async.queueCapacity:40}")
  private int asyncQueueCapacity;

  /**
   * 异步线程池
   */
  @Bean
  public ExecutorService asyncExecutor(MeterRegistry registry) {
    ExecutorService async = getExecutorService("async", asyncPoolSize, asyncQueueCapacity);
    return ExecutorServiceMetrics.monitor(registry,
        async,
        "async-pool",
        ""
    );
  }

  @Bean
  public ExecutorService appExecutor(MeterRegistry registry) {
    ExecutorService async = getExecutorService("app", asyncPoolSize, asyncQueueCapacity);
    return ExecutorServiceMetrics.monitor(registry,
        async,
        "app-pool",
        ""
    );
  }

  protected ExecutorService getExecutorService(String threadNamePrefix, int poolSize,
      int queueCapacity) {
    ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
    threadPoolTaskExecutor.setThreadNamePrefix(threadNamePrefix);
    threadPoolTaskExecutor.setCorePoolSize(poolSize);
    threadPoolTaskExecutor.setMaxPoolSize(poolSize);
    threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
    threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    threadPoolTaskExecutor.initialize();
    return threadPoolTaskExecutor.getThreadPoolExecutor();
  }
}
