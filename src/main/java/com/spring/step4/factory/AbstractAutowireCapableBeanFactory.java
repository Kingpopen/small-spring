package com.spring.step4.factory;

import cn.hutool.core.bean.BeanUtil;
import com.spring.step4.entity.BeanDefinition;
import com.spring.step4.entity.BeanReference;
import com.spring.step4.entity.PropertyValues;
import com.spring.step4.exception.BeanException;
import com.spring.step4.strategy.CglibStrategy;
import com.spring.step4.strategy.InstantiationStrategy;
import java.lang.reflect.Constructor;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 工厂抽象类 额外实现了bean的创建 和 存储
 * @date 2023/12/10 11:36:18
 */
@Slf4j
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

  // 相当于使用策略模式 来辅助 createBean方法的实现
  InstantiationStrategy strategy = new CglibStrategy();

  // 实现创建bean的方法
    /*
        总共三步：
        1. 创建bean
        2. 添加属性
        3. 将bean添加到singletonmap中
     */
  @Override
  protected Object createBean(String name, BeanDefinition beanDefinition, Object[] args) {
    Object bean = null;
    try {
      // 创建bean
      bean = createBeanInstance(beanDefinition, args);
      // 添加注入属性
      addField(bean, beanDefinition);
    } catch (Exception e) {
      throw new BeanException(String.format("创建bean异常:%s", e.getMessage()));
    }
    // 加入到单实例的管理
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

  // 注入属性
  private void addField(Object bean, BeanDefinition beanDefinition){
    PropertyValues propertyValues = beanDefinition.getPropertyValues();
    propertyValues.getPropertyValueList().forEach(item -> {
      String name = item.getName();
      Object val = item.getVal();

      if (val instanceof BeanReference){
        BeanReference beanReference = (BeanReference) val;
        val = getBean(beanReference.getName());
      }
      BeanUtil.setFieldValue(bean, name, val);
    });
  }
}
