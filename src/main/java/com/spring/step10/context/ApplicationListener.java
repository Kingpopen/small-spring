package com.spring.step10.context;

import java.util.EventListener;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 应用的监听 接口
 * @date 2024/01/31 22:47:00
 */
public interface ApplicationListener<T extends ApplicationEvent> extends EventListener {

  /**
   * @param event
   * 当监听到事件发生时需要执行的操作
   */
  void onApplicationEvent(T event);
}
