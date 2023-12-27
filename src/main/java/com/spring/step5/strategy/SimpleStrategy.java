package com.spring.step5.strategy;

import com.spring.step5.entity.BeanDefinition;
import com.spring.step5.exception.BeanException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 简单的实例化策略
 * @date 2023/12/10 17:14:24
 */
public class SimpleStrategy implements InstantiationStrategy {

  // 进行实例化
  @Override
  public Object instantiate(BeanDefinition beanDefinition, Constructor<?> ctor, Object[] args) {
    Object obj = null;
    Class<?> beanClazz = beanDefinition.getBean();
    try {
      if (!Objects.isNull(ctor)) {
        obj = beanClazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
      } else {
        obj = beanClazz.getDeclaredConstructor().newInstance();
      }
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
             NoSuchMethodException e) {
      throw new BeanException(String.format("实例化Bean失败:[%s]", e.getMessage()));
    }
    return obj;
  }
}
