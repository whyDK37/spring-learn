package com.atguigu.ext;

import com.atguigu.bean.Blue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 * 扩展原理：
 * BeanPostProcessor： bean后置处理器，bean创建对象初始化前后进行拦截工作的
 *
 * 1、BeanFactoryPostProcessor： beanFactory 的后置处理器；
 * 		在 BeanFactory 标准初始化之后调用，来定制和修改 BeanFactory 的内容；
 * 		所有的 bean 定义已经保存加载到 beanFactory，但是bean的实例还未创建
 *
 * BeanFactoryPostProcessor 原理:
 * 1)、ioc容器创建对象
 * 2)、invokeBeanFactoryPostProcessors(beanFactory);
 * 		如何找到所有的 BeanFactoryPostProcessor 并执行他们的方法；
 * 			1）、直接在 BeanFactory 中找到所有类型是 BeanFactoryPostProcessor 的组件，并执行他们的方法
 * 			2）、在初始化创建其他组件前面执行
 *
 * 2、BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor
 * 		postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
 * 		在所有bean定义信息将要被加载，bean实例还未创建的；
 *
 * 		优先于 BeanFactoryPostProcessor 执行；
 * 		利用 BeanDefinitionRegistryPostProcessor 给容器中再额外添加组件；
 *
 * 	 原理：
 * 		1）、ioc创建对象
 * 		2）、refresh() -> invokeBeanFactoryPostProcessors(beanFactory);
 * 		3）、从容器中获取到所有的 BeanDefinitionRegistryPostProcessor 组件。
 * 			1、依次触发所有的 postProcessBeanDefinitionRegistry() 方法
 * 			2、再来触发 postProcessBeanFactory() 方法 BeanFactoryPostProcessor；
 *
 * 		4）、再来从容器中找到 BeanFactoryPostProcessor 组件；然后依次触发 postProcessBeanFactory() 方法
 *
 *
 * 3、ApplicationListener：监听容器中发布的事件。事件驱动模型开发；
 * 	  public interface ApplicationListener<E extends ApplicationEvent>
 * 		监听 ApplicationEvent 及其下面的子事件；
 *
 * 	 步骤：
 * 		1）、写一个监听器（ApplicationListener实现类）来监听某个事件（ApplicationEvent及其子类）
 *            @EventListener; 添加到bean的方法上也可以监听事件
 *            原理：使用 EventListenerMethodProcessor 处理器来解析方法上的 @EventListener；
 *                  EventListenerMethodProcessor 实现了 SmartInitializingSingleton 接口，参见下面的原理解释
 * 		2）、把监听器加入到容器；
 * 		3）、只要容器中有相关事件的发布，我们就能监听到这个事件；
 * 				ContextRefreshedEvent： 容器刷新完成（所有bean都完全创建）会发布这个事件；
 * 				ContextClosedEvent： 关闭容器会发布这个事件；
 * 		4）、发布一个事件：
 * 				applicationContext.publishEvent()；
 *
 *  原理：
 *  	ContextRefreshedEvent、IOCTest_Ext$1[source=我发布的时间]、ContextClosedEvent；
 *  1）、ContextRefreshedEvent 事件：
 *  	1）、容器创建对象： refresh()；
 *  	2）、finishRefresh();容器刷新完成会发布 ContextRefreshedEvent 事件
 *  2）、自己发布事件；
 *  3）、容器关闭会发布 ContextClosedEvent；
 *
 *  【事件发布流程】：
 *  	3）、publishEvent(new ContextRefreshedEvent(this));
 *  			1）、获取事件的多播器（派发器）：getApplicationEventMulticaster()
 *  			2）、multicastEvent 派发事件：
 *  			3）、获取到所有的 ApplicationListener；
 *  				for (final ApplicationListener<?> listener : getApplicationListeners(event, type)) {
 *  				1）、如果有 Executor，可以支持使用 Executor 进行异步派发；
 *  					Executor executor = getTaskExecutor();
 *  				2）、否则，同步的方式直接执行 listener 方法； invokeListener(listener, event);
 *  				 拿到 listener 回调 onApplicationEvent 方法；
 *
 *  【事件多播器（派发器）】获取流程
 *  	1）、容器创建对象：refresh();
 *  	2）、initApplicationEventMulticaster();初始化 ApplicationEventMulticaster；
 *  		1）、先去容器中找有没有 id=“applicationEventMulticaster” 的组件；
 *  		2）、如果没有 this.applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
 *  			并且加入到容器中，我们就可以在其他组件要派发事件，自动注入这个 applicationEventMulticaster；
 *
 *  【容器中有哪些监听器】
 *  	1）、容器创建对象：refresh();
 *  	2）、registerListeners();
 *  		从容器中拿到所有的监听器，把他们注册到 applicationEventMulticaster 中；
 *  		String[] listenerBeanNames = getBeanNamesForType(ApplicationListener.class, true, false);
 *  		//将 listener 注册到 ApplicationEventMulticaster 中
 *  		getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);
 *
 *   SmartInitializingSingleton 原理： -> afterSingletonsInstantiated();
 *   		1）、ioc容器创建对象并 refresh()；
 *   		2）、finishBeanFactoryInitialization(beanFactory); 初始化剩下的单实例bean；
 *   			1）、先创建所有的单实例 bean；getBean();
 *   			2）、获取所有创建好的单实例 bean，判断是否是 SmartInitializingSingleton 类型的；
 *   				如果是就调用 afterSingletonsInstantiated();
 *
 * </pre>
 */
@ComponentScan("com.atguigu.ext")
@Configuration
public class ExtConfig {

    @Bean
    public Blue blue() {
        return new Blue();
    }

}
