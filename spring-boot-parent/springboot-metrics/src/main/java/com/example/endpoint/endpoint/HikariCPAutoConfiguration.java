//package com.example.endpoint.endpoint;
//
//import com.zaxxer.hikari.HikariDataSource;
//import javax.sql.DataSource;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class HikariCPAutoConfiguration {
//
//  @Bean
//  @ConditionalOnMissingBean
//  public HikariCPEndpoint hikariCPEndpoint(DataSource dataSource) {
//    return new HikariCPEndpoint((HikariDataSource) dataSource);
//  }
//}
