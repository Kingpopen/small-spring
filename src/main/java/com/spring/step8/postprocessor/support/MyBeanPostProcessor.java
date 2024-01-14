package com.spring.step8.postprocessor.support;

import com.spring.step8.UserService;
import com.spring.step8.postprocessor.BeanPostProcessor;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 自定义的BeanPostProcessor
 * @date 2024/1/2 19:42:29
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

  @Override
  public Object postProcessorBeforeInitialize(String beanName, Object bean) {
    if ("userService".equals(beanName)){
      UserService userService = (UserService) bean;
      userService.setIdentity("董事长");
      return userService;
    }
    return bean;
  }

  @Override
  public Object postProcessorAfterInitialize(String beanName, Object bean) {
    return bean;
  }
}
