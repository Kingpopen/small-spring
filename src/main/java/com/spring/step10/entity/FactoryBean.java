package com.spring.step10.entity;

/**
 * @author 彭锦波
 * @project small-spring
 * @description FactoryBean接口，用于给bean添加一些自定义的操作
 * @date 2024/1/14 17:20:59
 */
public interface FactoryBean<T> {
  T getObject();
  Class<?> getObjectType();

  boolean isSingleton();
}
