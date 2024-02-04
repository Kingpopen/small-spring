package com.spring.step10;

import com.spring.step10.context.ApplicationListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 学习的监听器
 * @date 2024/02/04 23:10:36
 */
@Slf4j
public class LearnListener implements ApplicationListener<LearnEvent> {

  // 当发生事件时, 执行相关的操作
  @Override
  public void onApplicationEvent(LearnEvent event) {
    log.info("hello, 大家好，我是练习{} 时长{} 的...", event.getName(), event.getHour());
  }
}
