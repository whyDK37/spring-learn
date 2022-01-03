package com.example.endpoint.config;

import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.jvm.ExecutorServiceMetrics;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;

/**
 * org.springframework.boot.actuate.autoconfigure.metrics 包下有很多自动注入的 metrics
 * </p>
 * 如：
 * </p>
 *
 * @see org.springframework.boot.actuate.autoconfigure.metrics.LogbackMetricsAutoConfiguration
 */
@Configuration
class MetricConfiguration {

    @Bean
    public CountedAspect countedAspect(MeterRegistry meterRegistry) {
        return new CountedAspect(meterRegistry, this::skipControllers);
    }

    @Bean
    public TimedAspect timedAspect(MeterRegistry meterRegistry) {
        return new TimedAspect(meterRegistry, this::skipControllers);
    }

    private boolean skipControllers(ProceedingJoinPoint pjp) {
        Class<?> targetClass = pjp.getTarget().getClass();
        return targetClass.isAnnotationPresent(RestController.class) || targetClass.isAnnotationPresent(Controller.class);
    }

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