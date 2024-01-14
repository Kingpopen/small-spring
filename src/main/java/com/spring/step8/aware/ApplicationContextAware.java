package com.spring.step8.aware;

import com.spring.step8.context.ApplicationContext;

/**
 * @author 彭锦波
 * @project small-spring
 * @description ApplicationContext的感知器
 * @date 2024/1/14 14:52:25
 */
public interface ApplicationContextAware extends Aware{
  void setApplicationContext(ApplicationContext applicationContext);
}
