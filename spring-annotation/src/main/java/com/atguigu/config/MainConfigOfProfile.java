package com.atguigu.config;


import com.atguigu.bean.Yellow;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

/**
 * <pre>
 * Profile：
 * 		Spring为我们提供的可以根据当前环境，动态的激活和切换一系列组件的功能；
 *
 * 开发环境、测试环境、生产环境；
 * 数据源：(/A)(/B)(/C)；
 *
 *
 * @ Profile：指定组件在哪个环境的情况下才能被注册到容器中，如果不指定，任何环境下都能注册这个组件
 *
 * 1）、加了环境标识的bean，只有这个环境被激活的时候才能注册到容器中。默认是default环境
 * 2）、写在配置类上，只有是指定的环境的时候，整个配置类里面的所有配置才能开始生效
 * 3）、没有标注环境标识的bean在，任何环境下都是加载的；
 *
 * org.springframework.core.env.AbstractEnvironment#ACTIVE_PROFILES_PROPERTY_NAME
 * 如何添加 profile
 * 1、使用命令行动态参数: 在虚拟机参数位置加载 -Dspring.profiles.active=test
 * 2、代码的方式激活某种环境；
 * </pre>
 */

@PropertySource("classpath:/dbconfig.properties")
@Configuration
public class MainConfigOfProfile implements EmbeddedValueResolverAware {

  @Value("${db.user}")
  private String user;

  private StringValueResolver valueResolver;

  private String driverClass;


  @Bean
  public Yellow yellow() {
    return new Yellow();
  }

  @Profile("test")
  @Bean("testDataSource")
  public DataSource dataSourceTest(@Value("${db.password}") String pwd) throws Exception {
    HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/test");
    hikariConfig.setUsername("root");
    hikariConfig.setPassword("root");
    hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
    return new HikariDataSource(hikariConfig);
  }


  @Profile("dev")
  @Bean("devDataSource")
  public DataSource dataSourceDev(@Value("${db.password}") String pwd) throws Exception {
    HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/test");
    hikariConfig.setUsername("root");
    hikariConfig.setPassword("root");
    hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
    return new HikariDataSource(hikariConfig);
  }

  @Profile("prod")
  @Bean("prodDataSource")
  public DataSource dataSourceProd(@Value("${db.password}") String pwd) throws Exception {
    HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/test");
    hikariConfig.setUsername("root");
    hikariConfig.setPassword("root");
    hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
    return new HikariDataSource(hikariConfig);
  }

  @Override
  public void setEmbeddedValueResolver(StringValueResolver resolver) {
    this.valueResolver = resolver;
    driverClass = valueResolver.resolveStringValue("${db.driverClass}");
  }

}
