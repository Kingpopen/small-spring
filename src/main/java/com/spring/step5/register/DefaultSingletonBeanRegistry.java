package com.spring.step5.register;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 单实例bean注册的抽象类
 * @date 2023/12/10 11:04:05
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

  private final Map<String, Object> singletonObjects = new HashMap<>();

  // 异常以及为空判断 放在外层
  public Object getSingleton(String name) {
    return singletonObjects.get(name);
  }

  // bean实例的添加
  protected void addSingleton(String name, Object singletonObject) {
    singletonObjects.put(name, singletonObject);
  }
}
