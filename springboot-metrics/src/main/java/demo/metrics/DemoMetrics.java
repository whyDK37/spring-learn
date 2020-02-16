package demo.metrics;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;

import java.util.concurrent.atomic.AtomicInteger;


public class DemoMetrics implements MeterBinder {

  AtomicInteger count = new AtomicInteger(0);

  @Override
  public void bindTo(MeterRegistry meterRegistry) {
    Gauge.builder("demo.count", count, c -> c.incrementAndGet())
        .tags("host", "localhost")
        .description("demo of custom meter binder")
        .register(meterRegistry);
  }
}