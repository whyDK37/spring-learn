
# 注意事项
1. 是用 idea 在子 module 运行程序是，user.dir 指向的是 root 工程，会导致某些问题。
   可以在启动的时候修改 user.dir 或者在 idea 的运行配置修改 working directory 指向当前 module 可解决问题。
   代码参考 org.springframework.boot.web.servlet.server.DocumentRoot.getCommonDocumentRoot。
   该方法返回文档根路径，用于配置嵌入 web 容器。

# 内容整理
- [spring web mvc content negotiation 内容协商](./doc/spring/web/spring-mvc-content-negotiation.md)  

# 项目结构
## [spring-annotation](./spring-annotation)
## [spring-framework](./spring-framework)
- [小马哥讲Spring核心编程思想](https://github.com/geektime-geekbang/geekbang-lessons.git) 

## [springboot-boot-parent](./spring-boot-parent)
spring boot 示例程序
#### spring boot demo

## [springboot-metrics](./spring-boot-parent/springboot-metrics)

http://127.0.0.1:8080/actuator/metrics/ 可访问所有的metrics.

http://127.0.0.1:8080/browser/index.html#/actuator 可查看所有的 endpoint

- [官方网址](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html#production-ready-metrics)
- [micrometer](http://micrometer.io/docs)

