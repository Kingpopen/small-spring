package com.spring.step9.factory;

/**
 * @author 彭锦波
 * @project small-spring
 * @description Bean初始化的接口
 * @date 2024/1/10 17:23:24
 */
public interface InitializingBean {
  // 初始化方法
  void afterPropertiesSet();
}
