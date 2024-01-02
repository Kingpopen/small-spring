package com.spring.step6.context.support;

import com.spring.step6.factory.ConfigurableListableBeanFactory;
import com.spring.step6.factory.support.DefaultListableBeanFactory;

/**
 * @author 彭锦波
 * @project small-spring
 * @description
 * @date 2024/1/2 07:54:01
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{
  DefaultListableBeanFactory beanFactory;
  // 导入beanDefinitions
  protected abstract void loadDefinitions(DefaultListableBeanFactory beanFactory);

  @Override
  protected void refreshBeanFactory() {
    // 创建 bean工厂
    beanFactory = createBeanFactory();
    // 导入 BeanDefinition
    loadDefinitions(beanFactory);
  }

  private DefaultListableBeanFactory createBeanFactory(){
    return new DefaultListableBeanFactory();
  }


  @Override
  protected ConfigurableListableBeanFactory getBeanFactory() {
    return beanFactory;
  }
}
