package com.spring.step7.factory;

import com.spring.step7.postprocessor.BeanPostProcessor;
import com.spring.step7.register.SingletonBeanRegistry;
import java.util.List;

/**
 * @author 彭锦波
 * @project small-spring
 * @description
 * @date 2024/1/1 20:37:16
 */
public interface ConfigurableBeanFactory extends BeanFactory, SingletonBeanRegistry {
  // 添加BeanPostProcessor
  void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

  List<BeanPostProcessor> getBeanPostProcessors();
}
