package com.spring.step10.context;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 事件的发布者
 * @date 2024/02/04 22:51:13
 */
public interface ApplicationEventPublisher {
  void publish(ApplicationEvent event);
}
