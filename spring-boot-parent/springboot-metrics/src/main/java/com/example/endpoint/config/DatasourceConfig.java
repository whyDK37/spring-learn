//package com.example.endpoint.config;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import java.util.Properties;
//import javax.sql.DataSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//
//@Configuration
//public class DatasourceConfig {
//
//  @Bean(name = "datasource")
//  public DataSource dataSource(Environment env) {
//    HikariConfig config = new HikariConfig();
//    config.setJdbcUrl(env.getProperty("spring.datasource.url"));
//    config.setUsername(env.getProperty("spring.datasource.username"));
//    config.setPassword(env.getProperty("spring.datasource.password"));
////    config.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
//
//    Properties properties = new Properties();
//    properties.setProperty("serverTimezone", env
//        .getProperty("spring.datasource.hikari.data-source-properties.serverTimezone"));
//    config.setDataSourceProperties(properties);
//    return new HikariDataSource(config);
//  }
//}
