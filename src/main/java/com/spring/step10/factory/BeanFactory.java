package com.spring.step10.factory;

/**
 * @author 彭锦波
 * @project small-spring
 * @description Bean工厂的接口，定义获取bean的规范。
 * @date 2023/12/10 10:29:42
 */
public interface BeanFactory {

  Object getBean(String name);

  Object getBean(String name, Object[] args);
}
