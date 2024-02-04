package com.spring.step10.aware;

import com.spring.step10.factory.BeanFactory;

/**
 * @author 彭锦波
 * @project small-spring
 * @description beanFactory的感知器
 * @date 2024/1/14 14:50:10
 */
public interface BeanFactoryAware extends Aware {
  void setBeanFactory(BeanFactory beanFactory);
}
