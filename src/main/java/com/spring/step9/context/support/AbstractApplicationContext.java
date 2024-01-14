package com.spring.step9.context.support;

import com.spring.step9.context.ConfigurableApplicationContext;
import com.spring.step9.core.loader.DefaultResourceLoader;
import com.spring.step9.factory.ConfigurableListableBeanFactory;
import com.spring.step9.postprocessor.BeanFactoryPostProcessor;
import com.spring.step9.postprocessor.BeanPostProcessor;
import com.spring.step9.postprocessor.support.ApplicationContextAwarePostProcessor;
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

  // 模版方法
  @Override
  public void refresh() {
    // 创建BeanFactory 并注册BeanDefinition
    refreshBeanFactory();
    // 获取BeanFactory
    ConfigurableListableBeanFactory beanFactory = getBeanFactory();

    // 添加一个ApplicationContextAware的后置处理器
    beanFactory.addBeanPostProcessor(new ApplicationContextAwarePostProcessor(this));

    // 执行BeanFactoryPostProcessor （在bean实例化之前）
    invokeBeanFactoryPostProcessors(beanFactory);
    // 注册BeanPostProcessor （在bean实例化之后 执行）
    registerBeanPostProcessors(beanFactory);
    // Bean的实例化
    beanFactory.preInstantiateSingletons();
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
    getBeanFactory().destroyBeans();
  }
}
