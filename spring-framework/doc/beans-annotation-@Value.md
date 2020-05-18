# spring @Value 只看这篇是不够的
spring 的 **@Value** 的使用频率是比较高的，一般我们是用来注入外部化配置，特别是 property 文件配置。
本文介绍 **@Value** 的常用方式。

## 基本使用
```java

// 应用到普通属性
// 如果没有匹配到，则使用 ${..}设置属性
// 单个值输入
@Value("${name}")
private String name;

// 多个值组合注入
@Value("${name}-${id}")
private String nameId;


// 如果没有匹配到，可以设置默认值
@Value("${noValue:defaultValue}")
private String defaultValue;

// 在构造方法使用
@Component
public class MyBean {

    private final String catalog;

    public MovieRecommender(@Value("${name}") String catalog) {
        this.catalog = catalog;
    }
}

```

```java
//systemProperties 是预定义的内部变量，可以通过它获取系统属性。
// 注入操作系统属性
@Value("#{systemProperties['os.name']}")
private String osName;

```

```java
// 如果@Value包含 SpEL，会动态生成值，当然 SpEL 要合法，否则会抛异常
// 结果：系统用户名+.why
@Value("#{systemProperties['user.name'] + '.why' }") 
private String catalog;

// 有了 Spel 的加成，可以支持复杂数据结构的注入，如
// map
@Value("#{{'Thriller': 100, 'Comedy': 300}}") 
Map<String, Integer> countOfMoviesPerCatalog
// list
@Value("#{'${demo.list}'.split(',')}")
private List<String> list;

```

```java
// 可以通过 @Value 注入 Resource 类型
@Value("http://www.baidu.com")
private Resource baidu; // 注入URL资源
resource 数组
@Value("classpath*:/META-INF/*.properties")
private Resource[] resources;
```

## 原理剖析
从使用上我们可以看到
@Value 有这多的使用方式，他是怎么实现的呢，我想到的第一步是看下 doc 上有没有蛛丝马迹。

```
Note that actual processing of the @Value annotation is performed by a BeanPostProcessor which in turn means that you cannot use @Value within BeanPostProcessor or BeanFactoryPostProcessor types. Please consult the javadoc for the AutowiredAnnotationBeanPostProcessor class (which, by default, checks for the presence of this annotation).
```
线索指向了 AutowiredAnnotationBeanPostProcessor 的 doc。

```
BeanPostProcessor implementation that autowires annotated fields, setter methods, and arbitrary config methods. Such members to be injected are detected through annotations: by default, Spring's @Autowired and @Value annotations.
Also supports JSR-330's @Inject annotation, if available, as a direct alternative to Spring's own @Autowired.
```

找对地方了，知道 AutowiredAnnotationBeanPostProcessor 是处理 Autowired，Value，Inject 注解的。
这里我就不不详细分析源码了，有几点注意的地方我标记一下。
- AutowiredAnnotationBeanPostProcessor 实现了 Ordered 接口所以有多个 BeanPostProcessor 时，他们的执行顺序对结果的影响需要注意，特别是自定义 BeanPostProcessor 时，一定要了解容器中现有生效的 bean 和 顺序，才能让自定义 BeanPostProcessor 在合适的位置执行。
- #postProcessProperties 方法实现了具体的注入逻辑。
  - AutowiredAnnotationBeanPostProcessor.AutowiredFieldElement#inject 
    - AutowireCapableBeanFactory#resolveDependency(org.springframework.beans.factory.config.DependencyDescriptor, java.lang.String, java.util.Set<java.lang.String>, org.springframework.beans.TypeConverter)  涉及到类型转换 
# 参考
- [1.9.8. Using @Value](https://docs.spring.io/spring/docs/5.3.0-SNAPSHOT/spring-framework-reference/core.html#beans-value-annotations)
- [Spring Expression Language (SpEL)](https://docs.spring.io/spring/docs/5.3.0-SNAPSHOT/spring-framework-reference/core.html#expressions)
