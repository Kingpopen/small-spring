package com.spring.step6.factory;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 自动注入的BeanFactory接口 属于二级接口
 * 添加用于执行 BeanPostProcessor方法 的方法
 * @date 2024/1/1 20:30:17
 */
public interface AutowireCapableBeanFactory extends BeanFactory{
  // BeanPostProcessor的before 方法
  Object applyBeanPostProcessorsBeforeInitialization(Object bean, String beanName);

  Object applyBeanPostProcessorsAfterInitialization(Object bean, String beanName);
}
