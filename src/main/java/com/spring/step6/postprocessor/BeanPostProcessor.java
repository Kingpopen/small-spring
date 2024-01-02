package com.spring.step6.postprocessor;

/**
 * @author 彭锦波
 * @project small-spring
 * @description Bean后置处理器
 * @date 2024/1/1 20:44:35
 */
public interface BeanPostProcessor {
  Object postProcessorBeforeInitialize(String beanName, Object bean);
  Object postProcessorAfterInitialize(String beanName, Object bean);
}
