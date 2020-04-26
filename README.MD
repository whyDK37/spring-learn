

# [spring-annotation](./spring-annotation)


# [springboot-boot-parent](./springboot-boot-parent)
## spring boot demo

1. 在子 module 运行程序是，user.dir 指向的是 root 工程，会导致某些问题。
   可以在启动的时候修改 user.dir 或者在 idea 的运行配置修改 working directory 指向当前 module 可解决问题。

# [springboot-metrics](./springboot-metrics)

http://127.0.0.1:8080/actuator/metrics/ 可访问所有的metrics.
http://127.0.0.1:8080/browser/index.html#/actuator 可查看所有的 endpoint
- [官方网址](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html#production-ready-metrics)
- [micrometer](http://micrometer.io/docs)

