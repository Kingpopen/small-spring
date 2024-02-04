package com.spring.step10;

import com.spring.step10.context.ApplicationEvent;
import lombok.Getter;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 学习事件
 * @date 2024/02/04 23:08:27
 */
@Getter
public class LearnEvent extends ApplicationEvent {

  /**
   * 学习的名称
   */
  private String name;
  /**
   * 学习的时长
   */
  private Integer hour;

  /**
   * Constructs a prototypical Event.
   *
   * @param source The object on which the Event initially occurred.
   * @throws IllegalArgumentException if source is null.
   */
  public LearnEvent(Object source, String name, Integer hour) {
    super(source);
    this.name = name;
    this.hour = hour;
  }
}
