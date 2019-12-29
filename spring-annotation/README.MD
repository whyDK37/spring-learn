> com.atguigu.config 类有详细的注解。

资料地址 https://gitee.com/adanzzz/spring_source_parsing_data
## 导入bean的方式：
1. 自己定义的类上添加@service，@Component，@Controller等
2. 第三方类，使用@bean
3. 使用@import，
    - @Import(Class.class) id是全类名
    - @Import(ImportSelector.class) 使用一个实现ImportSelector接口的类导入bean， ImportSelector 接口会返回需要导入的类名数组
    - @ImportBeanDefinitionRegistrar 导入bean定义， 和上面的接口类似，注册的是beanDefinition，可以定义name，作用域。
4. FactoryBean , 实现FactoryBean的类，spring 会调用 getObject获取对象，而不是对象本身。如果想获取FactoryBean对象，可使用 &FactoryBeanName 的方式
   其他框架和spring框架整合时，很多都是用的FactoryBean方式，比如 mybatis 的SessionFactory。

## bean 生命周期
销毁方法会在容器关闭的时候调用。
1. 可以通过 @Bean 注解定义 initMethod 和 destroyMethod 方法。
scope 是 prototype 的bean， initMethod方法会调用， destroyMethod 不会调用。
2. 实现 InitializingBean 和 DisposableBean 实现初始化和销毁。
3. 使用JSR250 定义的 @PostConstruct 和 @PreDestroy 注解。
4. BeanPostProcessor 后置处理器。接口中两个方法可以在bean构建之后或者初始化之后处理bean。

### bean初始化过程中，生命周期的相关方法
通过下面这个源码块，可以看到 bean 生命周期相关方法的调用逻辑。
```
/*
所有的bean都是在这个方法里创建的 org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean()
在 populateBean 方法中設置屬性
initializeBean 方法会依次调用
    invokeAwareMethods // 实现 Aware 接口
    applyBeanPostProcessorsBeforeInitialization // BeanPostProcessor 
    invokeInitMethods // 实现InitializingBean接口，定义 @PostConstruct 注解， @Bean initMethod 注解等
    applyBeanPostProcessorsAfterInitialization// BeanPostProcessor 
registerDisposableBeanIfNecessary // 注册销毁类
*/
protected Object doCreateBean(final String beanName, final RootBeanDefinition mbd, final @Nullable Object[] args)
        throws BeanCreationException {
    // ...
    try {
        // 設置屬性
        populateBean(beanName, mbd, instanceWrapper);
        exposedObject = initializeBean(beanName, exposedObject, mbd);
    }
    catch (Throwable ex) {
        if (ex instanceof BeanCreationException && beanName.equals(((BeanCreationException) ex).getBeanName())) {
            throw (BeanCreationException) ex;
        }
        else {
            throw new BeanCreationException(
                    mbd.getResourceDescription(), beanName, "Initialization of bean failed", ex);
        }
    }

    //...

    // Register bean as disposable.
    try {
        registerDisposableBeanIfNecessary(beanName, bean, mbd);
    }
    catch (BeanDefinitionValidationException ex) {
        throw new BeanCreationException(
                mbd.getResourceDescription(), beanName, "Invalid destruction signature", ex);
    }

    return exposedObject;
}

protected Object initializeBean(final String beanName, final Object bean, @Nullable RootBeanDefinition mbd) {
    if (System.getSecurityManager() != null) {
        AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
            invokeAwareMethods(beanName, bean);
            return null;
        }, getAccessControlContext());
    }
    else {
        invokeAwareMethods(beanName, bean);
    }

    Object wrappedBean = bean;
    if (mbd == null || !mbd.isSynthetic()) {
        wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
    }

    try {
        invokeInitMethods(beanName, wrappedBean, mbd);
    }
    catch (Throwable ex) {
        throw new BeanCreationException(
                (mbd != null ? mbd.getResourceDescription() : null),
                beanName, "Invocation of init method failed", ex);
    }
    if (mbd == null || !mbd.isSynthetic()) {
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
    }

    return wrappedBean;
}
```

### spring 内部使用 BeanPostProcessor
spring 内部大量使用 BeanPostProcessor 实现功能，我们可以看到 BeanPostProcessor 有很多实现类。
- ApplicationContextAwareProcessor 对于实现了 *Aware 接口的类，调用对应的方法设置参数。
- InitDestroyAnnotationBeanPostProcessor 处理 @PostConstruct @PreDestroy 注解并调用相关方法。
- AsyncAnnotationBeanPostProcessor 处理 @Async 异步梳理方法
- AutowiredAnnotationBeanPostProcessor @Autowired 注入


## 属性赋值
导入外部配置文件
1. @PropertySources 或 @PropertySource 导入外部配置文件。

为属性赋值
1. @Value
 - 基本数值，字符串
 - 可以写Spel, #{1+2}
 - 可以写 ${} 获取环境变量
 