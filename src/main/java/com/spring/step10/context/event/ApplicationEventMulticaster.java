package com.spring.step10.context.event;

import com.spring.step10.context.ApplicationEvent;
import com.spring.step10.context.ApplicationListener;

/**
 * @author 彭锦波
 * @project small-spring
 * @description ApplicationEvent 的派发器
 * @date 2024/01/31 22:50:37
 */
public interface ApplicationEventMulticaster {

  /**
   * @param listener
   * @desciption 添加监听器
   */
  void addListener(ApplicationListener<?> listener);

  /**
   * 移除监听的事件
   * @param listener
   */
  void removeListener(ApplicationListener<?> listener);

  /**
   * 进行事件的分发(找到相关监听器 进行执行)
   * @param event
   */
  void multicastEvent(ApplicationEvent event);
}
