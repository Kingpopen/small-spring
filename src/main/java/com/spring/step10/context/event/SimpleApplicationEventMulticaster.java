package com.spring.step10.context.event;

import com.spring.step10.context.ApplicationEvent;
import com.spring.step10.context.ApplicationListener;
import java.util.Collection;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 具体的事件派发类
 * @date 2024/01/31 23:19:56
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{


  // 实现具体的派发逻辑
  @Override
  public void multicastEvent(ApplicationEvent event) {
    // 获取该事件每一个的监听器
    Collection<ApplicationListener> applicationListeners = getApplicationListeners(event);
    // 执行监听器的方法
    applicationListeners.forEach(listener -> listener.onApplicationEvent(event));
  }
}
