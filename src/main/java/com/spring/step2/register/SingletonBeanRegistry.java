package com.spring.step2.register;

/**
 * @author 彭锦波
 * @project small-spring
 * @description bean的注册器接口, 定义规范
 * @date 2023/12/10 11:02:42
 */
public interface SingletonBeanRegistry {

  Object getSingleton(String name);
}
