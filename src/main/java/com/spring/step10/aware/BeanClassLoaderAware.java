package com.spring.step10.aware;

/**
 * @author 彭锦波
 * @project small-spring
 * @description Classloader的感知器
 * @date 2024/1/14 14:51:29
 */
public interface BeanClassLoaderAware extends Aware {
  void setClassLoader(ClassLoader classLoader);
}
