package com.spring.step7.factory;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 销毁bean
 * @date 2024/1/10 17:26:37
 */
public interface DisposableBean {
  // 销毁bean的方法
  void destroy() throws Exception;
}
