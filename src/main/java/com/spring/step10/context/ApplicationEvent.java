package com.spring.step10.context;

import java.util.EventObject;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 基础的事件 类
 * @date 2024/01/31 22:41:17
 */
public class ApplicationEvent extends EventObject {

  /**
   * Constructs a prototypical Event.
   *
   * @param source The object on which the Event initially occurred.
   * @throws IllegalArgumentException if source is null.
   */
  public ApplicationEvent(Object source) {
    super(source);
  }
}
