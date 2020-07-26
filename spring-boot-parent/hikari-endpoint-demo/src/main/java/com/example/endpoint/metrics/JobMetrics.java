//package com.example.endpoint.metrics;
//
//import io.micrometer.core.instrument.Counter;
//import io.micrometer.core.instrument.Gauge;
//import io.micrometer.core.instrument.MeterRegistry;
//import io.micrometer.core.instrument.Timer;
//import io.micrometer.core.instrument.binder.MeterBinder;
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.Map;
//import javax.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class JobMetrics  {
//
//  public Counter job1Counter;
//  public Counter job2Counter;
//  public Map<String, Double> map = new HashMap<>();
//  public Timer timer;
//
//
//  @Autowired
//  MeterRegistry meterRegistry;
//
//  @PostConstruct
//  public void bindTo() {
//    this.job1Counter = meterRegistry.counter("my_job","name","job1");
//    this.job2Counter = meterRegistry.counter("my_job","name","job2");
//    this.timer = meterRegistry.timer("my_job","name", "timer");
//  }
//
//  public void record(long millis) {
//    timer.record(Duration.ofMillis(millis));
//  }
//}