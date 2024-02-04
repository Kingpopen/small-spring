package com.spring.step10.context.support;

import com.spring.step10.context.ApplicationEvent;
import com.spring.step10.context.ApplicationListener;
import com.spring.step10.context.ConfigurableApplicationContext;
import com.spring.step10.context.event.ApplicationEventMulticaster;
import com.spring.step10.context.event.ContextClosedEvent;
import com.spring.step10.context.event.ContextRefreshedEvent;
import com.spring.step10.context.event.SimpleApplicationEventMulticaster;
import com.spring.step10.core.loader.DefaultResourceLoader;
import com.spring.step10.factory.BeanFactory;
import com.spring.step10.factory.ConfigurableListableBeanFactory;
import com.spring.step10.postprocessor.BeanFactoryPostProcessor;
import com.spring.step10.postprocessor.BeanPostProcessor;
import com.spring.step10.postprocessor.support.ApplicationContextAwarePostProcessor;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author 彭锦波
 * @project small-spring
 * @description
 * @date 2024/1/1 19:31:26
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements
    ConfigurableApplicationContext {

  // 事件发布者
  private ApplicationEventMulticaster multicaster;



  // 模版方法
  @Override
  public void refresh() {
    // 1. 创建BeanFactory 并注册BeanDefinition
    refreshBeanFactory();
    // 2. 获取BeanFactory
    ConfigurableListableBeanFactory beanFactory = getBeanFactory();

    // 3. 添加一个ApplicationContextAware的后置处理器
    beanFactory.addBeanPostProcessor(new ApplicationContextAwarePostProcessor(this));

    // 4. 执行BeanFactoryPostProcessor （在bean实例化之前）
    invokeBeanFactoryPostProcessors(beanFactory);
    // 5. 注册BeanPostProcessor （在bean实例化之后 执行）
    registerBeanPostProcessors(beanFactory);

    // 6. 初始化事件发布者
    initApplicationEventMulticaster();

    // 7. 注册监听器
    registerApplicationEventListener();

    // 8. Bean的实例化
    beanFactory.preInstantiateSingletons();

    // 9. 发布刷新结束的事件
    finishRefresh();

  }


  private void finishRefresh() {
    publish(new ContextRefreshedEvent(this));
  }

  private void registerApplicationEventListener() {
    // 1. 获取所有的listener 的bean
    Collection<ApplicationListener> values = getBeanFactory().getBeanOfType(
        ApplicationListener.class).values();
    // 2. 进行注册
    values.forEach(multicaster::addListener);
  }

  // 注册事件发布者
  private void initApplicationEventMulticaster() {
    // 创建发布者
    multicaster = new SimpleApplicationEventMulticaster();
  }


  // 创建 BeanFactory
  protected abstract void refreshBeanFactory();

  // 获取BeanFactory
  protected abstract ConfigurableListableBeanFactory getBeanFactory();


  private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory){
    Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeanOfType(
        BeanFactoryPostProcessor.class);

    beanFactoryPostProcessorMap.forEach((k, v) ->{
      v.postProcessBeanFactory(beanFactory);
    });
  }

  private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
    Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeanOfType(
        BeanPostProcessor.class);
    beanPostProcessorMap.forEach((k, v)->{
      beanFactory.addBeanPostProcessor(v);
    });
  }

  @Override
  public Object getBean(String name) {
    return getBeanFactory().getBean(name);
  }

  @Override
  public Object getBean(String name, Object[] args) {
    return getBeanFactory().getBean(name, args);
  }

  @Override
  public <T> Map<String, T> getBeanOfType(Class<T> type) {
    return getBeanFactory().getBeanOfType(type);
  }

  @Override
  public List<String> getAllDefinitionNames() {
    return getBeanFactory().getAllDefinitionNames();
  }

  @Override
  public void registerShutDownHook() {
    Runtime.getRuntime().addShutdownHook(new Thread(this::colse));
  }


  @Override
  public void colse() {
    // 发布关闭的事件
    publish(new ContextClosedEvent(this));

    getBeanFactory().destroyBeans();
  }

  // 调用发布器 发布事件
  @Override
  public void publish(ApplicationEvent event) {
    multicaster.multicastEvent(event);
  }
}
