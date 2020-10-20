package com.example.endpoint.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.jvm.ExecutorServiceMetrics;
import java.util.concurrent.ExecutorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * org.springframework.boot.actuate.autoconfigure.metrics 包下有很多自动注入的 metrics
 * </p>
 * 如：
 * </p>
 * @see org.springframework.boot.actuate.autoconfigure.metrics.LogbackMetricsAutoConfiguration
 */
@Configuration
class MetricConfiguration {

  @Bean("worker_pool")
  ExecutorService workerPool(MeterRegistry registry,
      ExecutorService asyncExecutor) {
    return ExecutorServiceMetrics.monitor(registry,
        asyncExecutor,
        "worker_pool",
        ""
    );
  }
}