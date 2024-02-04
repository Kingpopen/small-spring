package com.spring.step10.postprocessor;

import com.spring.step10.factory.ConfigurableListableBeanFactory;

/**
 * @author 彭锦波
 * @project small-spring
 * @description beanFactory 后置处理器:
 * 在所有的BeanDefinition 加载完成之后
 * 在bean的 实例化之前就执行的操作
 * @date 2023/12/30 20:27:39
 */
public interface BeanFactoryPostProcessor {
  void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory);
}
