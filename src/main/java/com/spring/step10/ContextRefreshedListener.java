package com.spring.step10;

import com.spring.step10.context.ApplicationListener;
import com.spring.step10.context.event.ContextRefreshedEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 上下文刷新完成事件监听器
 * @date 2024/02/04 23:26:31
 */
@Slf4j
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    log.info("上下文完成刷新 发起的类是:{}", event.getSource().getClass());
  }
}
