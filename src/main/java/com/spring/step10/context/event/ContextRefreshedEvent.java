package com.spring.step10.context.event;

import com.spring.step10.context.ApplicationEvent;

/**
 * @author 彭锦波
 * @project small-spring
 * @description Context刷新的事件类
 * @date 2024/01/31 22:46:02
 */
public class ContextRefreshedEvent extends ApplicationEvent {

  /**
   * Constructs a prototypical Event.
   *
   * @param source The object on which the Event initially occurred.
   * @throws IllegalArgumentException if source is null.
   */
  public ContextRefreshedEvent(Object source) {
    super(source);
  }
}
