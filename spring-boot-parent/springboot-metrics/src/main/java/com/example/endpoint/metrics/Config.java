package com.example.endpoint.metrics;

import com.example.endpoint.config.DemoMetrics;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class Config {

  @Bean
  public DemoMetrics demoMetrics() {
    return new DemoMetrics();
  }
}
