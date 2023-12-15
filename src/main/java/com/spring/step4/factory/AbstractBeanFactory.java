package com.spring.step4.factory;

import com.spring.step4.entity.BeanDefinition;
import com.spring.step4.register.DefaultSingletonBeanRegistry;
import java.util.Objects;

/**
 * @author 彭锦波
 * @project small-spring
 * @description Bean工厂的抽象类 提取出共有的内容
 * @date 2023/12/10 10:30:58
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements
    BeanFactory {

  // 实现获取bean的方法 定义了获取bean的流程： 模版方法 的设计模式
  public Object getBean(String name) {
    return doGetBean(name, null);
  }

  @Override
  public Object getBean(String name, Object[] args) {
    return doGetBean(name, args);
  }

  // 获取beanDefinition
  protected abstract BeanDefinition getBeanDefinition(String name);

  protected abstract Object createBean(String name, BeanDefinition beanDefinition, Object[] args);


  // 抽象出一个共有的方法
  protected Object doGetBean(String name, Object[] args){
    Object singleton = getSingleton(name);
    if (!Objects.isNull(singleton)) {
      return singleton;
    }

    BeanDefinition beanDefinition = getBeanDefinition(name);
    return createBean(name, beanDefinition, args);
  }
}
