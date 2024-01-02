package com.spring.step6.register;

import com.spring.step6.entity.BeanDefinition;

/**
 * @author 彭锦波
 * @project small-spring
 * @description BeanDefinition的注册器
 * @date 2023/12/10 11:56:55
 */
public interface BeanDefinitionRegistry {

  // 只有注册的方法 没有获取的方法 单一职责原则
  void registerBeanDefinition(String name, BeanDefinition beanDefinition);

  // 添加一个判断是否存在该bean的功能
  boolean containBeanDefinition(String name);
}
