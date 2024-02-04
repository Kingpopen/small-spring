package com.spring.step10;

import com.spring.step10.context.ApplicationListener;
import com.spring.step10.context.event.ContextClosedEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 上下文关闭的事件监听器
 * @date 2024/02/04 23:28:30
 */
@Slf4j
public class ContextClosedListener implements ApplicationListener<ContextClosedEvent> {

  @Override
  public void onApplicationEvent(ContextClosedEvent event) {
    log.info("上下文关闭 发起的类是:{}", event.getSource().getClass());
  }
}
