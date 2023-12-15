package com.spring.step2.factory;

/**
 * @author 彭锦波
 * @project small-spring
 * @description Bean工厂的接口，定义获取bean的规范。
 * @date 2023/12/10 10:29:42
 */
public interface BeanFactory {

  public Object getBean(String name);
}
