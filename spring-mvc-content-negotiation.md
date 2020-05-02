
# spring mvc Content negotiation 内容協商

## 核心组件
- 视图解析
  - ContentNegotiatingViewResolver
    - InternalResourceViewResolver
    - BeanNameViewResolver
    - ThymeleafViewResolver
- 配置策略
  - 配置 Bean： WebMvcConfigurer
  - 配置对象： ContentNegotiationConfigurer
- 策略管理
  - Bean： ContentNegotiationManager 
  - FactoryBean ： ContentNegotiationManagerFactoryBean
- 策略实现
  - ContentNegotiationStrategy
    - 固定 MediaType ： FixedContentNegotiationStrategy 
    - "Accept" 请求头： HeaderContentNegotiationStrategy
    - 请求参数： ParameterContentNegotiationStrategy
    - 路径扩展名： PathExtensionContentNegotiationStrategy


# 测试代码
```
# web 内容协商
curl -H "Accept-Language:zh-CN,zh;q=0.9" -H "Accept:text/html"  http://localhost:8080/web/hello
curl -H "Accept-Language:zh-CN,zh;q=0.9" -H "Accept:application/xml"  http://localhost:8080/web/hello

rest 内容协商
curl -X POST -H "Content-Type:application/json" --data '{"id":21,"name":"内容"}' http://localhost:8080/web/echo/user 
curl -X POST -H "Content-Type:application/json" -H "Accept:application/xml" --data '{"id":21,"name":"内容"}' http://localhost:8080/web/echo/user 
```
