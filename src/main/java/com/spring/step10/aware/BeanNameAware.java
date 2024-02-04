package com.spring.step10.aware;

/**
 * @author 彭锦波
 * @project small-spring
 * @description bean名字的Aware
 * @date 2024/1/14 14:48:42
 */
public interface BeanNameAware extends Aware {
  void setBeanName(String beanName);
}
