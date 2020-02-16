package me.data.redis;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class AppConfig {


  @Bean
  public RedisTemplate<String, String> redisTemplate(
      RedisConnectionFactory redisConnectionFactory) {
    StringRedisSerializer serializer = new StringRedisSerializer();
    RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory);
    redisTemplate.setDefaultSerializer(serializer);
    redisTemplate.setEnableTransactionSupport(true);
    return redisTemplate;
  }

  @Bean
  public LettuceConnectionFactory redisConnectionFactory() {
    return new LettuceConnectionFactory(new RedisStandaloneConfiguration("localhost", 6379));
  }

//  @Bean
//  public JedisConnectionFactory redisConnectionFactory() {
//    return new JedisConnectionFactory();
//  }

  @Bean
  public PlatformTransactionManager transactionManager() throws SQLException {
    return new DataSourceTransactionManager(dataSource());
  }

  @Bean
  public DataSource dataSource() throws SQLException {
    // ...
    BasicDataSource basicDataSource = new BasicDataSource();
    basicDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test");
    basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
    basicDataSource.setUsername("root");
    basicDataSource.setPassword("root");

    basicDataSource.setConnectionProperties("useUnicode=true;characterEncoding=UTF8;zeroDateTimeBehavior=convertToNull;socketTimeout=160000;connectTimeout=1000;autoReconnectForPools=true");
    basicDataSource.setInitialSize(10);
    basicDataSource.setInitialSize(10);
    basicDataSource.setMaxIdle(10);
    basicDataSource.setMinIdle(5);

    basicDataSource.setValidationQuery("SELECT 1");
    basicDataSource.setTestWhileIdle(true);
    basicDataSource.setTestOnReturn(false);
    basicDataSource.setTestOnBorrow(false);
    basicDataSource.setLogAbandoned(true);
    basicDataSource.setRemoveAbandonedTimeout(180);
    basicDataSource.setTimeBetweenEvictionRunsMillis(30000);
    basicDataSource.setMinEvictableIdleTimeMillis(1800000);
    basicDataSource.setNumTestsPerEvictionRun(50);

    return basicDataSource;
  }
}