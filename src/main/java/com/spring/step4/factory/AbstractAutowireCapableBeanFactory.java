package com.spring.step4.factory;

import com.spring.step3.BeanDefinition;
import com.spring.step4.exception.BeanException;
import com.spring.step4.strategy.CglibStrategy;
import com.spring.step4.strategy.InstantiationStrategy;
import java.lang.reflect.Constructor;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 工厂抽象类
 * @date 2023/12/10 11:36:18
 */
@Slf4j
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

  // 相当于使用策略模式 来辅助 createBean方法的实现
  InstantiationStrategy strategy = new CglibStrategy();

  // 实现创建bean的方法
    /*
        总共两步：
        1. 创建bean
        2. 将bean添加到singletonmap中
     */
  @Override
  protected Object createBean(String name, BeanDefinition beanDefinition, Object[] args) {
    Object bean = null;
    try {
      bean = createBeanInstance(beanDefinition, args);
    } catch (Exception e) {
      throw new BeanException(String.format("创建bean异常:%s", e.getMessage()));
    }
    addSingleton(name, bean);
    return bean;
  }

  protected Object createBeanInstance(BeanDefinition definition, Object[] args)
      throws NoSuchMethodException {
    Constructor constructorUse = null;
    Class clazz = definition.getBean();
    Constructor[] ctors = clazz.getDeclaredConstructors();
    for (Constructor ctor : ctors) {
      if (!Objects.isNull(args) && ctor.getParameters().length == args.length) {
        constructorUse = ctor;
        break;
      }
    }
    return strategy.instantiate(definition, constructorUse, args);
  }
}
