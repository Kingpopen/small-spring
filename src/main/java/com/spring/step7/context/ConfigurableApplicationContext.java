package com.spring.step7.context;

/**
 * @author 彭锦波
 * @project small-spring
 * @description：我现在觉得这个接口是通过配置 获取上下文的接口
 * @date 2024/1/1 19:26:00
 */
public interface ConfigurableApplicationContext extends ApplicationContext {
  // 刷新容器
  void refresh();

  // 注册虚拟机关闭时执行的钩子
  void registerShutDownHook();

  void colse();
}
