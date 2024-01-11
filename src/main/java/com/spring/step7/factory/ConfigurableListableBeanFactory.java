package com.spring.step7.factory;

import com.spring.step7.entity.BeanDefinition;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 四级接口
 * @date 2024/1/1 20:46:53
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory,
    AutowireCapableBeanFactory, ConfigurableBeanFactory {

  // 通过名字获取BeanDefinition
  BeanDefinition getBeanDefinition(String name);
  // 预先进行bean的实例化
  void preInstantiateSingletons();
}
