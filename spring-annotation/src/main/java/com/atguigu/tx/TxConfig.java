package com.atguigu.tx;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.InfrastructureAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration;
import org.springframework.transaction.annotation.TransactionManagementConfigurationSelector;
import org.springframework.transaction.annotation.Transactional;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * <pre>
 * 声明式事务：
 *
 * 环境搭建：
 * 1、导入相关依赖
 * 		数据源、数据库驱动、Spring-jdbc模块
 * 2、配置数据源、JdbcTemplate（Spring提供的简化数据库操作的工具）操作数据
 * 3、给方法上标注 @Transactional 表示当前方法是一个事务方法；
 * 4、@EnableTransactionManagement 开启基于注解的事务管理功能；
 *    @EnableXXX 就是开启什么功能
 * 5、配置事务管理器来控制事务; 注册事务管理器在容器中
 *        @Bean
 *        public PlatformTransactionManager transactionManager()
 *
 *
 * 原理：
 * 1）、@EnableTransactionManagement
 * 			利用 TransactionManagementConfigurationSelector 给容器中会导入两个组件
 * 			AutoProxyRegistrar
 * 			ProxyTransactionManagementConfiguration
 * 2）、AutoProxyRegistrar：
 * 			给容器中注册一个 InfrastructureAdvisorAutoProxyCreator 组件； 代码：AopConfigUtils.registerAutoProxyCreatorIfNecessary(registry);
 * 			InfrastructureAdvisorAutoProxyCreator：实现了 SmartInstantiationAwareBeanPostProcessor 接口。
 * 			利用后置处理器机制在对象创建以后，包装对象，返回一个代理对象（增强器），代理对象执行方法利用拦截器链进行调用；
 *
 * 3）、ProxyTransactionManagementConfiguration 是一个配置类，给容器增加组件。
 * 		1、给容器中注册事务增强器；
 * 			1）、事务增强器要用事务注解的信息，AnnotationTransactionAttributeSource 解析事务注解
 * 			2）、事务拦截器：
 * 				TransactionInterceptor；保存了事务属性信息，事务管理器；
 * 				他是一个 MethodInterceptor；
 * 			3. 1，2中的对象，构建了 BeanFactoryTransactionAttributeSourceAdvisor 对象，
 *
 * 			4. TransactionInterceptor 在目标方法执行的时候（invokeWithinTransaction 方法是主要逻辑）；
 * 					执行拦截器链；
 * 					事务拦截器：
 * 						1）、先获取事务相关的属性
 * 						2）、获取 PlatformTransactionManager，如果 @Transactional 有添加指定任何 transactionmanger
 * 							最终会从容器中按照类型获取一个 PlatformTransactionManager类型的对象；
 * 						3）、执行目标方法：retVal = invocation.proceedWithInvocation();
 * 							如果异常，获取到事务管理器，利用事务管理回滚操作；completeTransactionAfterThrowing(txInfo, ex);
 * 							如果正常，利用事务管理器，提交事务：commitTransactionAfterReturning(txInfo);
 * </pre>
 */
@EnableTransactionManagement
@ComponentScan("com.atguigu.tx")
@Configuration
public class TxConfig {

  //数据源
  @Bean
  public DataSource dataSource() throws Exception {
    ComboPooledDataSource dataSource = new ComboPooledDataSource();
    dataSource.setUser("root");
    dataSource.setPassword("123456");
    dataSource.setDriverClass("com.mysql.jdbc.Driver");
    dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
    return dataSource;
  }

  //
  @Bean
  public JdbcTemplate jdbcTemplate() throws Exception {
    //Spring 对 @Configuration 类会特殊处理；给容器中加组件的方法，多次调用都只是从容器中找组件
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
    return jdbcTemplate;
  }

  //注册事务管理器在容器中
  @Bean
  public PlatformTransactionManager transactionManager() throws Exception {
    return new DataSourceTransactionManager(dataSource());
  }


}
