package com.spring.step9.strategy;

import com.spring.step9.entity.BeanDefinition;
import java.lang.reflect.Constructor;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 实例化策略
 * @date 2023/12/10 17:09:47
 */
public interface InstantiationStrategy {

  // 实例化的方法
  Object instantiate(BeanDefinition beanDefinition, Constructor<?> ctor, Object[] args);
}
