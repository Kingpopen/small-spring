package com.spring.step10.context.event;

import com.spring.step10.context.ApplicationEvent;

/**
 * @author 彭锦波
 * @project small-spring
 * @description ApplicationContext的事件类
 * @date 2024/01/31 22:43:00
 */
public class ApplicationContextEvent extends ApplicationEvent {

  /**
   * Constructs a prototypical Event.
   *
   * @param source The object on which the Event initially occurred.
   * @throws IllegalArgumentException if source is null.
   */
  public ApplicationContextEvent(Object source) {
    super(source);
  }
}
