package com.spring.step2.factory;

import com.spring.step2.BeanDefinition;
import com.spring.step2.register.DefaultSingletonBeanRegistry;
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
    Object singleton = getSingleton(name);
    if (!Objects.isNull(singleton)) {
      return singleton;
    }

    BeanDefinition beanDefinition = getBeanDefinition(name);
    return createBean(name, beanDefinition);
  }

  // 获取beanDefinition
  protected abstract BeanDefinition getBeanDefinition(String name);

  protected abstract Object createBean(String name, BeanDefinition beanDefinition);
}
