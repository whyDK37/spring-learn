# 工程简介



# 延伸阅读

## application 配置

1. bootstrap.yaml 配置 nacos 的服务器地址

## 日志配置

1. 在 nacos 中的 application.properties 增加 配置
logging.config=http://${spring.cloud.nacos.config.server-addr}/nacos/v1/cs/configs?group=DEFAULT_GROUP&tenant=${spring.cloud.nacos.config.namespace}&dataId=logback-spring.xml

2. 在 nacos 中增加 logback-spring.xml 配置

> 注意：这种方式以来 spring.cloud.nacos.config.server-addr 的配置，需要配置成域名或者地址

## @value 刷新方式
1. 方式一@NacosValue获取最新值nacos配置信息需要写在配置类上

2. 方式二@NacosValue获取不到值(如果需要使用该注解需要引入方式一的jar，但是不重启服务获取不到最新值)，@Value获取最新值一定要加@RefreshScope注解，配置文件中配置refresh: true



