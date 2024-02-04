package com.spring.step10.context.event;

import com.spring.step10.context.ApplicationEvent;

/**
 * @author 彭锦波
 * @project small-spring
 * @description Context关闭的事件类
 * @date 2024/01/31 22:45:21
 */
public class ContextClosedEvent extends ApplicationEvent {

  /**
   * Constructs a prototypical Event.
   *
   * @param source The object on which the Event initially occurred.
   * @throws IllegalArgumentException if source is null.
   */
  public ContextClosedEvent(Object source) {
    super(source);
  }
}
