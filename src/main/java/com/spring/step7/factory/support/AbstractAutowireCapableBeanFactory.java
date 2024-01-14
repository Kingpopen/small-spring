package com.spring.step7.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.spring.step7.entity.BeanDefinition;
import com.spring.step7.entity.BeanReference;
import com.spring.step7.entity.PropertyValues;
import com.spring.step7.exception.BeanException;
import com.spring.step7.factory.AutowireCapableBeanFactory;
import com.spring.step7.factory.DisposableAdapter;
import com.spring.step7.factory.DisposableBean;
import com.spring.step7.factory.InitializingBean;
import com.spring.step7.postprocessor.BeanPostProcessor;
import com.spring.step7.strategy.CglibStrategy;
import com.spring.step7.strategy.InstantiationStrategy;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 彭锦波
 * @project small-spring
 * @description 工厂抽象类 额外实现了bean的创建 和 存储
 * @date 2023/12/10 11:36:18
 */
@Slf4j
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements
    AutowireCapableBeanFactory {

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
      // 初始化 和 执行 BeanPostProcessor
      bean = initializeBean(name, bean, beanDefinition);
    } catch (Exception e) {
      throw new BeanException(String.format("创建bean异常:%s", e.getMessage()));
    }

    // 注册销毁器
    registerDisposableBeanIfNecessary(bean, name, beanDefinition);

    // 加入到单实例的管理
    addSingleton(name, bean);
    return bean;
  }

  private void registerDisposableBeanIfNecessary(Object bean, String beanName, BeanDefinition beanDefinition) {
    if (bean instanceof DisposableBean || !StrUtil.isEmpty(beanDefinition.getDestroyMethodName())){
      registerDisposableBean(beanName, new DisposableAdapter(bean, beanDefinition));
    }
  }

  // Bean的实例化
  protected Object createBeanInstance(BeanDefinition definition, Object[] args) {
    Constructor<?> constructorUse = null;
    Class<?> clazz = definition.getBean();
    Constructor<?>[] ctors = clazz.getDeclaredConstructors();
    for (Constructor<?> ctor : ctors) {
      if (!Objects.isNull(args) && ctor.getParameters().length == args.length) {
        constructorUse = ctor;
        break;
      }
    }
    return strategy.instantiate(definition, constructorUse, args);
  }

  // 注入属性
  private void addField(Object bean, BeanDefinition beanDefinition) {
    PropertyValues propertyValues = beanDefinition.getPropertyValues();
    propertyValues.getPropertyValueList().forEach(item -> {
      String name = item.getName();
      Object val = item.getVal();

      if (val instanceof BeanReference) {
        BeanReference beanReference = (BeanReference) val;
        val = getBean(beanReference.getName());
      }
      BeanUtil.setFieldValue(bean, name, val);
    });
  }

  // Bean的初始化 和 执行 BeanPostProcessor
  private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition){
    // 初始化之前的操作
    bean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

    // 初始化操作
    invokeInitMethods(bean, beanDefinition);

    // 初始化之后的操作
    bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
    return bean;
  }

  private void invokeInitMethods(Object bean, BeanDefinition beanDefinition){
    if (bean instanceof InitializingBean){
      // 如果是实现了 InitializingBean 接口的bean
      ((InitializingBean)bean).afterPropertiesSet();
      return;
    }

    // 如果是采用了 配置文件定义的 初始化方法
    if (!StrUtil.hasEmpty(beanDefinition.getInitMethodName())){
      // 通过反射执行该方法
      Class<?> clazz = beanDefinition.getBean();
      try {
        Method method = clazz.getMethod(beanDefinition.getInitMethodName());
        method.invoke(bean);
      } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
        log.info("没有找到方法:[{}] bean is:{}, beanDefinition is:{}", beanDefinition.getDestroyMethodName(), bean, beanDefinition, e);
        throw new BeanException("没有找到方法!");
      }
    }
  }

  // 实例 初始化之前的操作
  @Override
  public Object applyBeanPostProcessorsAfterInitialization(Object bean, String beanName) {
    List<BeanPostProcessor> beanPostProcessors = getBeanPostProcessors();
    for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
      Object tmp = beanPostProcessor.postProcessorAfterInitialize(beanName, bean);
      if (Objects.isNull(tmp))
        break;
      bean = tmp;
    }
    return bean;
  }

  // 实例 初始化之后的操作
  @Override
  public Object applyBeanPostProcessorsBeforeInitialization(Object bean, String beanName) {
    List<BeanPostProcessor> beanPostProcessors = getBeanPostProcessors();
    for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
      Object tmp = beanPostProcessor.postProcessorBeforeInitialize(beanName, bean);
      if (Objects.isNull(tmp))
        break;
      bean = tmp;
    }
    return bean;
  }
}
